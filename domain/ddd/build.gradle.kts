plugins {
    alias(libs.plugins.app.jvm.library)
}

dependencies {
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlinx.datetime)
    implementation(project(":domain:inventory"))
    implementation(project(":base:utils"))
    implementation(project(":features:inventorycreation"))
}