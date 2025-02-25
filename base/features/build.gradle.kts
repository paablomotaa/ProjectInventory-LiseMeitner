plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.hilt)
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
    namespace = "app.base.features"
}

dependencies {
    implementation(projects.domain.ddd)
    implementation(projects.domain.inventory)
    implementation(projects.base.utils)

    // Android
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Kotlinx
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.runtime.android)

    // Unit Testing
    testImplementation(kotlin("test"))
    testImplementation(libs.junit)
}