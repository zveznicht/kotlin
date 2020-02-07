plugins {
    kotlin("jvm") version "1.3.70"
    application
}

repositories {
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
}

val toolingApiVersion = "6.2.2"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.gradle:gradle-tooling-api:$toolingApiVersion")
    // The tooling API need an SLF4J implementation available at runtime, replace this with any other implementation
    runtimeOnly("org.slf4j:slf4j-simple:1.7.10")
}