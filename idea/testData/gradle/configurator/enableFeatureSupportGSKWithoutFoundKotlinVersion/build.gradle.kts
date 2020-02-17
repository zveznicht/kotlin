buildscript {
    ext {
        kotlinVersion = "1.3.60"
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
