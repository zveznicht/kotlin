/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.generators.tests

import java.io.File

private data class ParsedMethod(
    val returnType: String,
    val funName: String,
    val parameters: String,
    val body: String
)

private data class ParsedCallComponent(
    val callee: String,
    val typeArguments: String?,
    val valueArguments: String?
)

private data class ParsedCall(
    val jspecifyMark: String?,
    val receiver: String,
    val callComponents: List<ParsedCallComponent>
)

class GenerateKotlinUseSitesFromJavaOnesForJspecifyTests {
    companion object {
        private const val TESTS_DIRECTORY = "compiler/testData/foreignAnnotationsJava8/tests/jspecify/java"

        private const val JSPECIFY_ANNOTATIONS = """@(?:Nullable|NullnessUnspecified)"""
        private const val JSPECIFY_MARK = """\/\/ (?:jspecify_nullness_mismatch)\n\s*"""
        private const val TYPE_COMPONENT = """(?:$JSPECIFY_ANNOTATIONS )?(?:\w+)(?:\.\w+)*"""
        private const val TYPE_ARGUMENTS = """<(?:$TYPE_COMPONENT)(?:\s*,\s*$TYPE_COMPONENT)*>"""
        private const val TYPE_PARAMETER = """\w(?: (?:extends|super) $TYPE_COMPONENT)?"""
        private const val TYPE_PARAMETERS = """\s*<(?:$TYPE_PARAMETER)(?:\s*,\s*$TYPE_PARAMETER)*>\s*"""
        private const val TYPE = """$TYPE_COMPONENT(?:$TYPE_ARGUMENTS)?"""
        private const val VALUE_PARAMETER = """\s*(?:$TYPE) (?:\w+)"""
        private const val VALUE_PARAMETERS = """\((?:$VALUE_PARAMETER)(?:\s*,\s*$VALUE_PARAMETER)*\s*\)"""
        private const val VALUE_ARGUMENT = """\w+"""
        private const val VALUE_ARGUMENTS = """\((?:(?:$VALUE_ARGUMENT)(?:\s*,\s*$VALUE_ARGUMENT)*\s*)?\)"""
        private const val CALL_COMPONENT = """\.($TYPE_ARGUMENTS)?(?:\w+)(?:$VALUE_ARGUMENTS)?"""
        private const val CALL = """\s*(?:$JSPECIFY_MARK)?(?:\w+)(?:(?:$CALL_COMPONENT)+);"""
        private const val FUN_BODY = """\{((?:$CALL)+)\s*}"""

        val methodToConvertRegex = Regex("""(?:public )?(?:$TYPE_PARAMETERS)?(void|$TYPE) (\w+)($VALUE_PARAMETERS)? $FUN_BODY""")
        val valueParameterRegex = Regex("""($TYPE) (\w+)(,|\s*$)""")
        val callRegex = Regex(CALL.replace("?:", ""))
        val callComponentRegex = Regex(CALL_COMPONENT.replace("?:", ""))
    }

    private fun parseMethod(sourceCode: String): ParsedMethod? {
        val a = methodToConvertRegex.find(sourceCode) ?: return null
        val (returnType, funName, arguments, body) = a.destructured

        return ParsedMethod(
            transformTypesByAnnotationsIfNeeded(returnType),
            funName,
            arguments.removeSurrounding("(", ")"),
            body
        )
    }

    private fun parseCalls(funBody: String) =
        callRegex.findAll(funBody).toList().map { call ->
            val jspecifyMark = call.groups[2]?.value
            val receiver = call.groups[3]!!.value
            val callComponents = call.groups[4]!!
            val parsedCallComponents = callComponentRegex.findAll(callComponents.value).toList().map { callComponent: MatchResult ->
                val calleName = callComponent.groups[12]!!.value
                val typeArguments = callComponent.groups[1]?.value
                val valueArguments = callComponent.groups[13]?.value?.removeSurrounding("(", ")")
                    ?.let { transformTypesByAnnotationsIfNeeded(it) }
                ParsedCallComponent(calleName, typeArguments, valueArguments)
            }

            ParsedCall(jspecifyMark, receiver, parsedCallComponents)
        }

    private fun transformTypesByAnnotationsIfNeeded(valueArguments: String) =
        valueArguments
            .replace(Regex("""@Nullable (\w+)"""), "$1?")
            .replace("Object", "Any")
            .replace("void", "Unit")

    private fun generateKotlinCode(parsedMethod: ParsedMethod, parsedCalls: List<ParsedCall>) = buildString {
        val methodArguments = transformTypesByAnnotationsIfNeeded(
            parsedMethod.parameters.replace(valueParameterRegex, "$2: $1$3")
        )
        append("fun ${parsedMethod.funName}($methodArguments): ${parsedMethod.returnType} {\n")
        for (call in parsedCalls) {
            append(" ".repeat(8))
            append("${call.receiver}.")
            for (callComponent in call.callComponents) {
                append("${callComponent.callee}${callComponent.typeArguments ?: ""}(${callComponent.valueArguments})")
            }
            append(";\n")
        }
        append("}")
    }

    fun main() {
        val file = File("$TESTS_DIRECTORY/WildcardsWithDefault.java")
        val parsedMethod = parseMethod(file.readText()) ?: return
        val parsedCalls = parseCalls(parsedMethod.body)

        val generatedKotlinCode = generateKotlinCode(parsedMethod, parsedCalls)

        File("${file.parentFile.parent}/kotlin/WildcardsWithDefault.kt").writeText("// JAVA_SOURCES: WildcardsWithDefault.java\n\n" + generatedKotlinCode)
    }
}

fun main() {
    GenerateKotlinUseSitesFromJavaOnesForJspecifyTests().main()
}