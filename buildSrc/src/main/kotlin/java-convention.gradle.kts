import io.franzbecker.gradle.lombok.task.DelombokTask

plugins {
    id("io.franzbecker.gradle-lombok")
    `java-library`
}

repositories {
    mavenCentral()
}

lombok {
    version = Versions.LOMBOK
    sha256 = null
}

dependencies {
    annotationProcessor(Dependencies.JABEL)
    testAnnotationProcessor(Dependencies.JABEL)

    compileOnly(Dependencies.JB_ANNOTATIONS)
    testCompileOnly(Dependencies.JB_ANNOTATIONS)

    testImplementation(Dependencies.JUNIT_JUPITER)
    testRuntimeOnly(Dependencies.JUNIT_PLATFORM)
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = "16" // Jabel compatibility for IDEs
    options.release = 8 // Target language level
    options.encoding = "UTF-8"

    // Jabel compatibility
    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(16)
    }
}

tasks {
    val delombok by registering(DelombokTask::class) {
        dependsOn(compileJava)
        val outputDir by extra { file("${layout.buildDirectory}/delombok") }
        outputs.dir(outputDir)
        sourceSets["main"].java.srcDirs.forEach {
            inputs.dir(it)
            args(it, "-d", outputDir)
        }
        doFirst {
            outputDir.delete()
        }
    }
    javadoc {
        // Lombok compatibility
        dependsOn(delombok)
        val outputDir: File by delombok.get().extra
        source = fileTree(outputDir)
        isFailOnError = false
        // Jabel compatibility
        javadocTool = javaToolchains.javadocToolFor {
            languageVersion = JavaLanguageVersion.of(16)
        }
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
