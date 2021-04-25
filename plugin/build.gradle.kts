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
    implementation("com.android.tools.build:gradle:4.1.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.mlegy.linkester"
version = "0.0.5"

pluginBundle {
    website = "https://github.com/mlegy/linkester"
    vcsUrl = "https://github.com/mlegy/linkester.git"
    tags = listOf("android", "deep_links", "app_links", "testing")
}

gradlePlugin {
    plugins {
        register("linkester") {
            id = "com.mlegy.linkester"
            displayName = "Linkester Gradle Plugin"
            description = "A plugin for linkester library to collect all deep links automatically."
            implementationClass = "com.melegy.linkester.LinkesterPlugin"
        }
    }
}
