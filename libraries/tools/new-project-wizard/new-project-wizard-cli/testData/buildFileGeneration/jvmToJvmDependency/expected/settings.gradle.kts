pluginManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }

}
rootProject.name = "generatedProject"


include(":a")
include(":b")
include(":c")
include(":d")