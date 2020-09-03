import org.jetbrains.kotlin.pill.PillExtension

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

dependencies {
    compileOnly(project(":idea"))

    compileOnly(intellijDep())
    compileOnly(intellijPluginDep("git4idea"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {  }
}
