import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
    id("com.diffplug.spotless") version "6.12.1"
}

group = "com.haagwee"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jgrapht:jgrapht-core:1.5.1")
    implementation("org.jetbrains.kotlinx:multik-core:0.2.1")
    implementation("org.jetbrains.kotlinx:multik-default:0.2.1")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

spotless { // if you are using build.gradle.kts, instead of 'spotless {' use:
           // configure<com.diffplug.gradle.spotless.SpotlessExtension> {
  kotlin {
    // by default the target is every '.kt' and '.kts` file in the java sourcesets
    ktfmt()    // has its own section below
    // ktlint()   // has its own section below
    // diktat()   // has its own section below
    // prettier() // has its own section below
  }
  kotlinGradle {
    // ktlint() // or ktfmt() or prettier()
  }
}