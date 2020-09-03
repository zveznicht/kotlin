import org.jetbrains.kotlin.pill.PillExtension

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

pill {
    variant = PillExtension.Variant.IDE
}

dependencies {
    compile(kotlinStdlib())
    compile(project(":compiler:backend"))

    compileOnly(toolsJarApi())
    compileOnly(intellijCoreDep()) { includeJars("intellij-core", "asm-all", rootProject = rootProject) }

    testCompileOnly(toolsJarApi())
    testCompile(project(":kotlin-test:kotlin-test-junit"))
    testCompile(commonDep("junit:junit"))
    testCompile(intellijDep()) { includeJars("asm-all", rootProject = rootProject) }
}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}

projectTest(parallel = true) {
    dependsOn(":dist")
    workingDir = rootDir
}
