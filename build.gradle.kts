import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.sviams.aoc.2020"
version = "1.0-SNAPSHOT"

val kotlin_version = "1.4.20"
val spek_version = "2.0.9"
val kluent_version = "1.64"


plugins {
    kotlin("jvm") version "1.4.20"
    application
}


repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testImplementation("org.amshove.kluent:kluent:$kluent_version")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version")

    // spek requires kotlin-reflect, can be omitted if already in the classpath
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.4.20")
}

tasks.test {
    useJUnitPlatform {
        includeEngines = setOf("spek2")
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}