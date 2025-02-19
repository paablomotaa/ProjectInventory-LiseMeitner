plugins {
    alias(libs.plugins.app.jvm.library)
    alias(libs.plugins.kotlin.ksp)
}

dependencies {
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlinx.datetime)
    implementation(project(":domain:inventory"))
    implementation(project(":base:utils"))

    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.androidx.room.common)
    ksp(libs.room.compiler)
    annotationProcessor(libs.room.compiler)
}