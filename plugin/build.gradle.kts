plugins {
    kotlin("jvm") version "1.4.32"
    id("java-gradle-plugin")
    id("maven-publish")
}

repositories {
    jcenter()
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation("com.android.tools.build:gradle:4.1.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.melegy.linkester"
version = "0.0.5"

gradlePlugin {
    plugins {
        create("linkester") {
            id = "com.melegy.linkester"
            displayName = "Linkester Gradle Plugin"
            description = "A Gradle plugin for linkester library to collect all deep links automatically."
            implementationClass = "com.melegy.linkester.LinkesterPlugin"
        }
    }
}
