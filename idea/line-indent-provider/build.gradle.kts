import org.jetbrains.kotlin.pill.PillExtension

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

dependencies {
    compile(project(":compiler:psi"))
    compileOnly(intellijCoreDep()) { includeJars("intellij-core", rootProject = rootProject) }
    compileOnly(intellijDep()) { includeJars("platform-api", "platform-impl", rootProject = rootProject) }
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

