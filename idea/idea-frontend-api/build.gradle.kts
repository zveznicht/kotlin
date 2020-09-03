import org.jetbrains.kotlin.pill.PillExtension

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

dependencies {
    compileOnly(project(":kotlin-reflect-api"))

    compileOnly(project(":compiler:psi"))
    compileOnly(project(":compiler:frontend"))
    compileOnly(project(":core:compiler.common"))
    compileOnly(project(":idea:idea-frontend-independent"))
    compileOnly(project(":compiler:psi"))
    compileOnly(intellijCoreDep())
    compileOnly(intellijDep())

    Platform[191].orLower {
        compileOnly(intellijDep()) { includeJars("java-api", "java-impl") }
    }

    Platform[192].orHigher {
        compileOnly(intellijPluginDep("java")) { includeJars("java-api", "java-impl") }
    }
}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}

testsJar()

projectTest {
    dependsOn(":dist")
    workingDir = rootDir
}
