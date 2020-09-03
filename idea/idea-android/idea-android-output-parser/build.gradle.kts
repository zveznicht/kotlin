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
    compileOnly(intellijCoreDep()) { includeJars("intellij-core") }
    compileOnly(intellijDep())
    compileOnly(intellijPluginDep("gradle"))
    compileOnly(intellijPluginDep("android"))
}

sourceSets {
    if (Ide.IJ() && Platform[183].orLower()) {
        "main" {
            projectDefault()
        }
    } else {
        "main" {}
    }
    "test" {}
}

runtimeJar()
