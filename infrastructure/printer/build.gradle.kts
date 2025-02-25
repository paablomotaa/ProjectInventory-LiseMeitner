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
    namespace = "app.infrastructure.printer"
}

dependencies {
    // Módulos
    implementation(projects.domain.ddd)
    implementation(projects.domain.inventory)
    implementation(projects.infrastructure.firebase)

    // Flujos
    implementation(libs.kotlin.coroutines.android)

    // Utilería
    implementation(libs.kotlinx.datetime)
}