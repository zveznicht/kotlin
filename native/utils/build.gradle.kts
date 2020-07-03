plugins {
    kotlin("jvm")
    id("jps-compatible")
}

description = "Kotlin/Native utils"

dependencies {
    compileOnly(kotlinStdlib())
    compile(project(":kotlin-util-io"))

    testCompile(commonDep("junit:junit"))
    testCompileOnly(project(":kotlin-reflect-api"))
    testRuntime(project(":kotlin-reflect"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}


// There are two consumers of this module as a separate published artifact:
// - K/N and it's build infrastructure - until migration to monorepo is finished.
// - Kotlin Gradle plugin - until a separate public API for host info is implemented (KT-39994).
// TODO: Stop publishing this artifact when it's no longer needed.
publish()

standardPublicJars()
