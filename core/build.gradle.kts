plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}
dependencies {
    implementation(project(mapOf("path" to ":common")))

    // Date Time
    api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

}
