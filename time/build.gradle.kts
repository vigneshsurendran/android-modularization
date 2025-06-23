import com.azabost.quest.build.Config

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.testFixtures)
}
java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}
kotlin {
    compilerOptions {
        jvmTarget = Config.kotlinJvmTarget
    }
}

dependencies {
    implementation(libs.javax.inject)
}
