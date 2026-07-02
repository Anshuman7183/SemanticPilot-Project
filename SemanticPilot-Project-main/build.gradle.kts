import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.intellij.platform")
    id("org.jetbrains.changelog")
}

group = "com.semanticpilot"
version = "1.0.0"

repositories {

    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {

    // ---------------------------
    // Testing
    // ---------------------------

    testImplementation(
        "junit:junit:4.13.2"
    )

    // ---------------------------
    // Coroutines
    // ---------------------------

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2"
    )

    // ---------------------------
    // Ktor HTTP Client
    // ---------------------------

    implementation(
        "io.ktor:ktor-client-core:3.1.3"
    )

    implementation(
        "io.ktor:ktor-client-cio:3.1.3"
    )

    implementation(
        "io.ktor:ktor-client-content-negotiation:3.1.3"
    )

    implementation(
        "io.ktor:ktor-serialization-kotlinx-json:3.1.3"
    )

    implementation(
        "io.ktor:ktor-client-logging:3.1.3"
    )

    implementation(
        "io.ktor:ktor-client-auth:3.1.3"
    )

    implementation(
        "io.ktor:ktor-client-resources:3.1.3"
    )

    implementation(
        "io.ktor:ktor-client-encoding:3.1.3"
    )

    // ---------------------------
    // Kotlin Serialization
    // ---------------------------

    implementation(
        "org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1"
    )

    // ---------------------------
    // IntelliJ Platform
    // ---------------------------

    intellijPlatform {

        intellijIdeaCommunity(
            "2025.2.6.2"
        )

    }
}

tasks {

    withType<JavaCompile> {

        sourceCompatibility = "21"

        targetCompatibility = "21"
    }

    withType<KotlinCompile> {

        compilerOptions {

            jvmTarget.set(
                JvmTarget.JVM_21
            )
        }
    }
}
