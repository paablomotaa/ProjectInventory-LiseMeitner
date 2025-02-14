plugins {
    alias(libs.plugins.app.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlin.coroutines.core)
    implementation(project(":base:utils"))
    implementation(project(":base:utils"))
    implementation(project(":base:utils"))
    testImplementation(libs.jupiter.junit.jupiter)
    testImplementation(libs.junit)

}