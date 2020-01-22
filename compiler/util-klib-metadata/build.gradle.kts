import org.gradle.api.artifacts.maven.Conf2ScopeMappingContainer.COMPILE

plugins {
    maven
    kotlin("jvm")
    id("jps-compatible")
}

val mavenCompileScope by configurations.creating {
    the<MavenPluginConvention>()
        .conf2ScopeMappings
        .addMapping(0, this, COMPILE)
}

description = "Common klib metadata reader and writer"

dependencies {
     // Compile-only dependencies are needed for compilation of this module:
    compileOnly(project(":compiler:cli-common"))
    compileOnly(project(":compiler:frontend"))
    compileOnly(project(":core:deserialization"))
    compileOnly(project(":compiler:serialization"))

    // This dependency is necessary to keep the right dependency record inside of POM file:
    mavenCompileScope(project(":kotlin-compiler"))

    compile(kotlinStdlib())
    compile(project(":kotlin-util-io"))
    compile(project(":kotlin-util-klib"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { none() }
}

publish()

standardPublicJars()
