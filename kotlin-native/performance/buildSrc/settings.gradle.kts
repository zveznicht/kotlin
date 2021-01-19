import java.io.File
pluginManagement {
    val cacheRedirectorEnabled = true//project(".").findProperty("cacheRedirectorEnabled")?.toString()?.toBoolean() == true
    repositories {
        if (cacheRedirectorEnabled) {
            logger.info("Using cache redirector for settings.gradle pluginManagement")
            maven("https://cache-redirector.jetbrains.com/plugins.gradle.org/m2")
        } else {
            gradlePluginPortal()
        }
        maven("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space//kotlin/p/kotlin/bootstrap")
    }
}

buildscript {
    val cacheRedirectorEnabled = true //gradle.findProject(".").findProperty("cacheRedirectorEnabled")?.toString()?.toBoolean() == true
    repositories {
        if (cacheRedirectorEnabled) {
            maven("https://cache-redirector.jetbrains.com/kotlin.bintray.com/kotlin-dependencies")
        } else {
            maven("https://kotlin.bintray.com/kotlin-dependencies")
        }
        maven("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space//kotlin/p/kotlin/bootstrap")

    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-build-gradle-plugin:0.0.24-minamoto-0")
    }
}

//include("tools")
