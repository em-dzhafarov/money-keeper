plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Settings.DI}"
}

dependencies {
    implementProjects(
        AppProject.Settings.Presentation,
        AppProject.Settings.UI,
        AppProject.Settings.Data,
        AppProject.Settings.Domain
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}