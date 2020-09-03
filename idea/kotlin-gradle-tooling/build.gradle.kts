import org.jetbrains.kotlin.pill.PillExtension

description = "Kotlin Gradle Tooling support"

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

jvmTarget = "1.6"

dependencies {
    compile(kotlinStdlib())

    compileOnly(intellijPluginDep("gradle"))
    compileOnly(intellijDep()) { includeJars("slf4j-api-1.7.25") }
    Platform[193].orLower {
        compile(project(":idea:idea-gradle-tooling-api"))
    }
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

runtimeJar()

sourcesJar()

javadocJar()

apply(from = "$rootDir/gradle/kotlinPluginPublication.gradle.kts")
