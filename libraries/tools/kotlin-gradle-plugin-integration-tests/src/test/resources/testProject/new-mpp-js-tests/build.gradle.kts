plugins {
    kotlin("multiplatform").version("<pluginMarkerVersion>")
}

repositories {
    mavenLocal()
    maven { url 'https://cache-redirector.jetbrains.com/repo1.maven.org/maven2/' }
    jcenter()
}

kotlin {
    dependencies {
        commonMainImplementation(kotlin("stdlib-common"))
        commonTestApi(kotlin("test-common"))
    }

    val jsCommon = js("jsCommon") {
        dependencies {
            commonMainImplementation(kotlin("stdlib-js"))
            commonTestApi(kotlin("test-js"))
        }
    }

    js("server")
    js("client")
}