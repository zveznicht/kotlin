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
    compileOnly(intellijDep())
    Platform[192].orHigher {
        compileOnly(intellijPluginDep("java"))
    }
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

