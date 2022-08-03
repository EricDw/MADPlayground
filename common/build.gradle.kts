plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {

    val coroutinesVersion = "1.6.4"

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testApi("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}