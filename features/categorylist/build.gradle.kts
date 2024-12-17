plugins {
    alias(libs.plugins.app.android.feature)
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "app.features.categorylist"
}

dependencies {
    implementation(projects.domain.inventory)
    implementation(libs.coil.compose)
    implementation(project(":features:inventorycreation"))
    implementation(project(":features:categorycreation"))
}
