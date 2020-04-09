pluginManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }

}
rootProject.name = "generatedProject"


include(":b:c")
include(":b")