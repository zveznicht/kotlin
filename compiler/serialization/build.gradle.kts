plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    compile(project(":compiler:resolution"))
    compile(project(":compiler:frontend"))
    compile(project(":core:deserialization"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
