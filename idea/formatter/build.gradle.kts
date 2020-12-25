
plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    compile(project(":compiler:util"))
    compile(project(":compiler:psi"))
    compileOnly(intellijDep())
    compileOnly(intellijPluginDep("java"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

