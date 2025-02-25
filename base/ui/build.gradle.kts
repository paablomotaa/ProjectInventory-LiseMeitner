plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.compose)
}

android {
    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0",
                "META-INF/*.kotlin_module",
                "META-INF/gradle/incremental.annotation.processors"
            )
        )
    }
    namespace = "app.base.ui"

}

dependencies {
    implementation(projects.base.utils)
    implementation(projects.domain.ddd)
    implementation(libs.kotlinx.datetime)
    implementation(libs.android.lottie)
    implementation(libs.androidx.appcompat)
    implementation(libs.androix.compose)
    implementation(libs.coil.compose)
}