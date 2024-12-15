plugins {
    alias(libs.plugins.app.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlin.coroutines.core)
    implementation(project(":base:utils"))
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.navigation.runtime.ktx)

}