plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.8.0"
}

group = "com.examen.bi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javaFxVersion = "20"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("org.openjfx:javafx-base:$javaFxVersion")
    implementation("org.openjfx:javafx-controls:$javaFxVersion")
    implementation("org.openjfx:javafx-fxml:$javaFxVersion")
    implementation("org.openjfx:javafx-graphics:$javaFxVersion")
    implementation("org.openjfx:javafx-base:$javaFxVersion")
    implementation("org.openjfx:javafx-controls:$javaFxVersion")
    implementation("org.openjfx:javafx-fxml:$javaFxVersion")
    implementation("org.openjfx:javafx-graphics:$javaFxVersion")

}


tasks.withType<JavaExec> {
    val osName = System.getProperty("os.name").toLowerCase()
    val platform = when {
        osName.contains("win") -> "win"
        osName.contains("mac") -> "mac"
        osName.contains("linux") -> "linux"
        else -> throw GradleException("Unsupported OS: $osName")
    }
    jvmArgs(
        "--module-path",
        "$buildDir/libs/javafx-sdk-$javaFxVersion/lib",
        "--add-modules",
        "javafx.controls,javafx.fxml"
    )
}

tasks.withType<JavaExec> {
    val osName = System.getProperty("os.name").toLowerCase()
    val platform = when {
        osName.contains("win") -> "win"
        osName.contains("mac") -> "mac"
        osName.contains("linux") -> "linux"
        else -> throw GradleException("Unsupported OS: $osName")
    }
    jvmArgs(
        "--module-path",
        "$buildDir/libs/javafx-sdk-$javaFxVersion/lib",
        "--add-modules",
        "javafx.base,javafx.controls"
    )
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}