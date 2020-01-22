plugins {
    kotlin("jvm")
    id("jps-compatible")
}

description = "Kotlin/Native library utils"

dependencies {
    compileOnly(project(":compiler:frontend"))
    compileOnly(project(":compiler:frontend.java"))
    compileOnly(project(":compiler:cli-common"))

    compile(project(":kotlin-native:kotlin-native-utils"))
    compile(project(":kotlin-util-io"))
    compile(project(":kotlin-util-klib"))
    compile(project(":kotlin-util-klib-metadata"))

    testCompile(commonDep("junit:junit"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}

publish()

standardPublicJars()
