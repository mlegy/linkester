plugins {
    kotlin("jvm") version "1.4.32"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.14.0"
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

group = "com.mlegy"
version = "0.0.1"

pluginBundle {
    website = "https://github.com/mlegy/linkester"
    vcsUrl = "https://github.com/mlegy/linkester.git"
    tags = listOf("android", "deep_links", "app_links", "testing")
}

gradlePlugin {
    plugins {
        create("linkester") {
            id = "com.mlegy.linkester"
            displayName = "Linkester Gradle Plugin"
            description = "A plugin for linkester library to collect all deep links automatically."
            implementationClass = "com.melegy.linkester.LinkesterPlugin"
        }
    }
}
