val kv = "1.3.60"

buildscript {
    ext {
        kotlinVersion = kv
    }
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

apply(plugin  ="kotlin")
