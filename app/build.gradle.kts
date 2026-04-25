plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
   // alias(libs.plugins.android.application)
   // alias(libs.plugins.kotlin.android) //
   // alias(libs.plugins.google.gms.google.services) //
}

android {
    namespace = "com.example.flatbookingapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.flatbookingapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Existing Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // --- FIREBASE & GOOGLE AUTH (ADDED & UPDATED) ---
    // Using BOM ensures all firebase versions work perfectly together
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    //implementation("com.google.firebase:firebase-auth-ktx") //
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Legacy credentials (kept for compatibility)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Retrofit & Network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Coroutines & Lifecycle
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.play.services.maps)

    // Image Loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // --- UNCOMMON FEATURES (KEPT) ---

    // 1. AI Recommendations (TensorFlow Lite)
    implementation("org.tensorflow:tensorflow-lite:2.14.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.4.4")

    // 2. Payments (Stripe SDK)
    implementation("com.stripe:stripe-android:20.37.2")

    // 3. Lottie Animations (For smooth Payment/Loading UI)
    implementation("com.airbnb.android:lottie:6.3.0")

    // 4. Data Visualization (Charts)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}