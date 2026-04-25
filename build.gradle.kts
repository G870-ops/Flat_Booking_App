plugins {
<<<<<<< HEAD
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // UPDATED: Added specific version 4.4.4 as requested
    id("com.google.gms.google-services") version "4.4.4" apply false
}
=======
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    dependencies {
        // Ktor Server
        implementation(libs.ktor.server.core)
        implementation(libs.ktor.server.netty)
        implementation(libs.ktor.server.content.negotiation)
        implementation(libs.ktor.serialization.kotlinx.json)

        // Database
        implementation(libs.exposed.core)
        implementation(libs.exposed.jdbc)
        implementation(libs.h2)

        // Logging
        implementation(libs.logback.classic)

        // Testing
        testImplementation(libs.ktor.server.test.host) // This replaces ktor-server-tests
        testImplementation(libs.kotlin.test.junit)

        implementation("org.postgresql:postgresql:42.6.0")
    }
}
>>>>>>> 2c0e7e79584e2f7d5a36a82b32a9a5f7e651e177
