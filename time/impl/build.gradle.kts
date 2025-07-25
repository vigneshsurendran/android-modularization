plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.time.api)
    implementation(libs.kotlin.stdlib)
    implementation(libs.hilt.core)
    implementation(libs.javax.inject)
}