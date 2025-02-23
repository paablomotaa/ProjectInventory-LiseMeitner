plugins {
    alias(libs.plugins.app.jvm.library)
    alias(libs.plugins.kotlin.ksp)
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(project(":base:utils"))
}