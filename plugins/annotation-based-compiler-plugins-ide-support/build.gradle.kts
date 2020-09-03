import org.jetbrains.kotlin.pill.PillExtension

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

dependencies {
    compile(project(":compiler:util"))
    compile(project(":compiler:frontend"))
    compile(project(":compiler:cli-common"))
    compile(project(":idea"))
    compile(project(":idea:idea-jvm"))
    compile(project(":idea:idea-jps-common"))
    compile(project(":idea:idea-gradle"))
    compile(project(":idea:idea-maven"))
    excludeInAndroidStudio(rootProject) { compileOnly(intellijPluginDep("maven")) }
    compileOnly(intellijPluginDep("gradle"))
    compileOnly(intellijDep())
    compileOnly(project(":idea:kotlin-gradle-tooling"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

