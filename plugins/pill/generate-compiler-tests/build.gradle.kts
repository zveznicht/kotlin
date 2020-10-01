
plugins {
    kotlin("jvm")
    id("jps-compatible")
}

val depenencyProjects = arrayOf(
    ":compiler",
    ":js:js.tests",
    ":compiler:tests-java8",
    ":core:descriptors.runtime"
)

dependencies {
    depenencyProjects.forEach {
        testCompile(projectTests(it))
        jpsTest(project(it, configuration = "jpsTest"))
    }

    // Workaround for IDEA Gradle importer
    if (System.getProperty("idea.active") != null) {
        testRuntimeOnly(files("${rootProject.projectDir}/dist/kotlinc/lib/kotlin-reflect.jar"))
    }
}

sourceSets {
    "main" { }
    "test" { projectDefault() }
}

testsJar()