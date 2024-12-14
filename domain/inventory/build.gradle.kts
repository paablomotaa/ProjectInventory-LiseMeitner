plugins {
    alias(libs.plugins.app.jvm.library)
}

dependencies {
    implementation(projects.base.utils)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlin.coroutines.core)

}