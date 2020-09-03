import org.jetbrains.kotlin.pill.PillExtension

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

sourceSets {
    "main" { }
    "test" { projectDefault() }
}

dependencies {
    testCompile(projectTests(":compiler:cli"))
    testCompile(projectTests(":idea:idea-maven"))
    testCompile(projectTests(":idea:idea-fir"))
    testCompile(projectTests(":idea:idea-frontend-fir"))
    testCompile(projectTests(":idea:idea-frontend-fir:idea-fir-low-level-api"))
    testCompile(projectTests(":j2k"))
    testCompile(projectTests(":nj2k"))

    if (Ide.IJ()) {
        testCompile(projectTests(":libraries:tools:new-project-wizard:new-project-wizard-cli"))
        testCompile(projectTests(":idea:idea-new-project-wizard"))
    }

    testCompile(projectTests(":idea:idea-android"))
    testCompile(projectTests(":idea:performanceTests"))
    testCompile(projectTests(":idea:scripting-support"))
    testCompile(projectTests(":jps-plugin"))
    testCompile(projectTests(":plugins:jvm-abi-gen"))
    testCompile(projectTests(":plugins:android-extensions-compiler"))
    testCompile(projectTests(":plugins:android-extensions-ide"))
    testCompile(projectTests(":plugins:parcelize:parcelize-compiler"))
    testCompile(projectTests(":plugins:parcelize:parcelize-ide"))
    testCompile(projectTests(":kotlin-annotation-processing"))
    testCompile(projectTests(":kotlin-annotation-processing-cli"))
    testCompile(projectTests(":kotlin-allopen-compiler-plugin"))
    testCompile(projectTests(":kotlin-noarg-compiler-plugin"))
    testCompile(projectTests(":kotlin-sam-with-receiver-compiler-plugin"))
    testCompile(projectTests(":kotlinx-serialization-compiler-plugin"))
    testCompile(projectTests(":plugins:fir:fir-plugin-prototype"))
    testCompile(projectTests(":idea:jvm-debugger:jvm-debugger-test"))
    testCompile(projectTests(":generators:test-generator"))
    testCompile(projectTests(":idea"))
    testCompileOnly(project(":kotlin-reflect-api"))
    testRuntime(intellijDep()) { includeJars("idea_rt") }
    testRuntime(project(":kotlin-reflect"))

    if (Ide.IJ()) {
        testCompileOnly(jpsBuildTest())
        testCompile(jpsBuildTest())
    }
}

projectTest(parallel = true) {
    workingDir = rootDir
}

val generateTests by generator("org.jetbrains.kotlin.generators.tests.GenerateTestsKt")

testsJar()
