import org.jetbrains.kotlin.pill.PillExtension

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

dependencies {
    testCompile(project(":compiler:frontend"))
    testCompile(projectTests(":compiler:tests-common"))
    testCompile(project(":idea"))
    testCompile(project(":idea:idea-jvm"))
    testCompile(project(":idea:idea-core"))
    testCompile(project(":idea:idea-jps-common"))
    testCompile(project(":kotlin-test:kotlin-test-jvm"))
    testCompileOnly(project(":kotlin-reflect-api"))
    testCompile(commonDep("junit:junit"))
    testCompileOnly(intellijDep())
    Platform[192].orHigher {
        testCompileOnly(intellijPluginDep("java"))
    }
}

sourceSets {
    "main" { }
    "test" { projectDefault() }
}

testsJar()