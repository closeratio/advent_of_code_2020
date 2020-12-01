import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
    }
}

plugins {
    java
    kotlin("jvm") version "1.4.20"
}

allprojects {

    apply(plugin = "kotlin")
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {

        if (project.name.startsWith("day_")) {
            implementation(project(":aoc_2020:common"))
        }

        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
        testImplementation("org.hamcrest:hamcrest-library:2.2")
    }

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
}


