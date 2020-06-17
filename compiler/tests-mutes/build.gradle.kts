plugins {
    kotlin("jvm")
    id("jps-compatible")
    application
}

dependencies {
    api(kotlinStdlib())
    implementation("khttp:khttp:1.0.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
}

sourceSets {
    "main" {
        projectDefault()
    }
    "test" {}
}

application {
    mainClassName = "org.jetbrains.kotlin.test.mutes.MutedTestsSyncKt"
}