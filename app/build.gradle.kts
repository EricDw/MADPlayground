plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.madplayground"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.madplayground"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":cache")))
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    // Core Extensions
    implementation("androidx.core:core-ktx:1.8.0")

    // region Window Manager

    implementation("androidx.window:window:1.1.0-alpha03")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha15")

    // endregion Window Manager

    // region Compose

    val composeUiVersion = "1.3.0-alpha02"
    val material3Version = "1.0.0-alpha15"

    // Compose Activity
    implementation("androidx.activity:activity-compose:1.5.1")

    // Compose UI
    implementation("androidx.compose.ui:ui:$composeUiVersion")

    // Compose Preview Tooling
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")

    // Material 2 for Compose
    implementation("androidx.compose.material:material:$composeUiVersion")

    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended:$composeUiVersion")

    // Material 3 for Compose
    implementation("androidx.compose.material3:material3:$material3Version")

    // endregion Compose

    // region Lifecycle

    val lifecycleVersion = "2.6.0-alpha01"

    // Lifecycle Runtime
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // ViewModel Kotlin Extensions
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // ViewModel Utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

    // Java8 Lifecycle Compiler - faster incremental builds
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    // endregion Lifecycle

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.1")

    // region Hilt

    val hiltVersion = "2.43.1"

    // Hilt for Android
    implementation("com.google.dagger:hilt-android:$hiltVersion")

    // Hilt Compiler
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // Hilt Compose Navigation Integration
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // endregion Hilt

    // region Testing

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Coroutine Testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // Compose Testing

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")

    // endregion Testing
}