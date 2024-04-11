import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "info.firozansari"
version = "2.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("$group.MainKt")
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.3.0")
    implementation("com.github.ajalt.mordant:mordant:2.4.0")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
