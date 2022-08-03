plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.cache"
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                          "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")

    // DataStore
    api("androidx.datastore:datastore-preferences:1.0.0")
    implementation(project(mapOf("path" to ":data")))

    // region Database

    val roomVersion = "2.4.3"

    api("androidx.room:room-runtime:$roomVersion")
    api("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    testApi("androidx.room:room-testing:$roomVersion")

    // endregion Database

    // region Hilt
    val hiltVersion = "2.43.1"

    // Hilt for Android
    implementation("com.google.dagger:hilt-android:$hiltVersion")

    // Hilt Compiler
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // endregion Hilt

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}