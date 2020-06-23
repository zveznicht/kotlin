/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.test.mutes

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import khttp.responses.Response
import khttp.structures.authorization.Authorization
import java.io.File

fun main() {
    syncMutedTestsOnTeamCityWithDatabase()
}

/**
 * Synchronize muted tests on teamcity with database flaky tests
 *
 * Purpose: running flaky tests on teamcity that do not affect on build status
 */
fun syncMutedTestsOnTeamCityWithDatabase() {
    val remotelyMutedTests = getMutedTestsOnTeamcityForRootProject(Scope.COMMON)
    for (scope in Scope.values()) {
        val remotelyMutedTestsForSpecificScope: Map<String, MuteTestJson> = filterMutedTestsByScope(remotelyMutedTests, scope)
        val locallyMutedTests: Map<String, MuteTestJson> = getMutedTestsFromDatabase(scope)
        val deleteList = remotelyMutedTestsForSpecificScope - locallyMutedTests.keys
        val uploadList = locallyMutedTests - remotelyMutedTestsForSpecificScope.keys
        deleteMutedTests(deleteList)
        uploadMutedTests(uploadList)
    }
}

private fun getMutedTestsFromDatabase(scope: Scope): Map<String, MuteTestJson> {
    val mutedSet = MutedSet(loadMutedTests(scope.localDBPath))
    val mutedMap = mutableMapOf<String, MuteTestJson>()
    for (muted in mutedSet.mutedFlakyTests()) {
        val testName = formatClassnameWithInnerClasses(muted.key)
        mutedMap[testName] = createMuteTestJson(testName, muted.issue ?: "", scope)
    }
    return mutedMap
}

private fun formatClassnameWithInnerClasses(classname: String): String {
    val classFindRegex = "\\.(?=[A-Z])".toRegex()
    val (pkg, name) = classname.split(classFindRegex, limit = 2)
    return "$pkg.${name.replace(classFindRegex, "\\$")}"
}

private val authUser = object : Authorization {
    override val header = "Authorization" to "Bearer ${System.getProperty("org.jetbrains.kotlin.test.mutes.buildserver.token")}"
}
private val headers = mapOf("Content-type" to "application/json", "Accept" to "application/json")
private const val TAG = "[MUTED-BY-CSVFILE]"
private val objectMapper = jacksonObjectMapper()


private fun getMutedTestsOnTeamcityForRootProject(scope: Scope): List<MuteTestJson> {
    val projectScope = scope.id

    val params = mapOf(
        "locator" to "project:(id:$projectScope)",
        "fields" to "mute(id,assignment(text),scope(project(id),buildTypes(buildType(id))),target(tests(test(name))),resolution)"
    )

    val response = khttp.get("https://buildserver.labs.intellij.net/app/rest/mutes", headers, params, auth = authUser)
    checkResponseAndLog(response)

    val alreadyMutedTestsOnTeamCity = objectMapper.readTree(response.text).get("mute")
        .filter { jn -> jn.get("assignment").get("text").textValue().startsWith(TAG) }

    return objectMapper.readValue(alreadyMutedTestsOnTeamCity.toString())
}

private fun filterMutedTestsByScope(muteTestJson: List<MuteTestJson>, scope: Scope): Map<String, MuteTestJson> {
    val filterCondition = { testJson: MuteTestJson ->
        if (scope.isBuildType)
            testJson.scope.get("buildTypes") != null &&
                    testJson.scope.get("buildTypes").get("buildType").toList().map {
                        it.get("id").textValue()
                    }.contains(scope.id)
        else
            testJson.scope.get("project") != null &&
                    testJson.scope.get("project").get("id").textValue() == scope.id
    }

    return muteTestJson.filter(filterCondition)
        .flatMap { mutedTestJson ->
            mutedTestJson.getTests().map { testname ->
                testname to mutedTestJson
            }
        }
        .toMap()
}

private fun uploadMutedTests(uploadMap: Map<String, MuteTestJson>) {
    for ((_, muteTestJson) in uploadMap) {
        val response = khttp.post(
            "https://buildserver.labs.intellij.net/app/rest/mutes",
            headers = headers,
            data = objectMapper.writeValueAsString(muteTestJson),
            auth = authUser
        )
        checkResponseAndLog(response)
    }
}

private fun deleteMutedTests(deleteMap: Map<String, MuteTestJson>) {
    for ((_, muteTestJson) in deleteMap) {
        val response = khttp.delete(
            "https://buildserver.labs.intellij.net/app/rest/mutes/id:${muteTestJson.id}",
            headers = headers,
            auth = authUser
        )
        checkResponseAndLog(response)
    }
}

private fun checkResponseAndLog(response: Response) {
    val isResponseBad = response.connection.responseCode / 100 != 2
    if (isResponseBad) {
        throw Exception(
            "${response.request.method}-request to ${response.request.url} failed:\n" +
                    "${response.text}\n" +
                    "${response.request.data ?: ""}"
        )
    }
}

data class MuteTestJson(
    val id: Int,
    val assignment: JsonNode,
    val scope: JsonNode,
    val target: JsonNode,
    val resolution: JsonNode
)

private fun MuteTestJson.getTests(): List<String> {
    return target.get("tests").get("test").toList().map { it.get("name").textValue() }
}

private fun createMuteTestJson(testName: String, description: String, scope: Scope): MuteTestJson {
    val assignmentJson = """{ "text" : "$TAG $description" }"""
    val scopeJson = if (scope.isBuildType)
        """{"buildTypes":{"buildType":[{"id":"${scope.id}"}]}}"""
    else
        """{"project":{"id":"${scope.name}"}}"""
    val targetJson = """{ "tests" : { "test" : [ { "name" : "$testName" } ] } }"""
    val resolutionJson = """{ "type" : "manually" }"""

    return MuteTestJson(
        0,
        objectMapper.readTree(assignmentJson),
        objectMapper.readTree(scopeJson),
        objectMapper.readTree(targetJson),
        objectMapper.readTree(resolutionJson)
    )
}


//FIXME change to gradle property usage
private const val tempDBPathDir = "../../tests"
private const val mutesPackageName = "org.jetbrains.kotlin.test.mutes"

// FIX ME WHEN BUNCH 192 REMOVED
// FIX ME WHEN BUNCH as36 REMOVED
enum class Scope(val id: String, val localDBPath: File, val isBuildType: Boolean) {
    COMMON("Kotlin_BuildPlayground_Jupiter_Tests", File("$tempDBPathDir/mute-common.csv"), false),
    IJ193(System.getProperty("$mutesPackageName.193"), File("$tempDBPathDir/mute-platform.csv"), true),
    IJ192(System.getProperty("$mutesPackageName.192"), File("$tempDBPathDir/mute-platform.csv.192"), true),
    IJ201(System.getProperty("$mutesPackageName.201"), File("$tempDBPathDir/mute-platform.csv.201"), true),
    AS36(System.getProperty("$mutesPackageName.as36"), File("$tempDBPathDir/mute-platform.csv.as36"), true),
    AS40(System.getProperty("$mutesPackageName.as40"), File("$tempDBPathDir/mute-platform.csv.as40"), true);
}