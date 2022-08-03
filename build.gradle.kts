buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.0-alpha07" apply false
    id("com.android.library") version "7.4.0-alpha07" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("org.jetbrains.kotlin.kapt") version "1.7.0" apply false
}