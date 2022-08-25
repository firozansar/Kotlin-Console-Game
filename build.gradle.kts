plugins {
    kotlin("jvm") version "1.6.20"
    application
}

group = "info.firozansari"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("$group.MainKt")
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.test { useJUnitPlatform() }
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() { kotlinOptions.jvmTarget = "11" }