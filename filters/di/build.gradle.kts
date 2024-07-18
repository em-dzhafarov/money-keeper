plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Filters.DI}"
}

dependencies {
    implementProjects(
        AppProject.Filters.Data,
        AppProject.Filters.Domain,
        AppProject.Filters.Presentation,
        AppProject.Filters.UI
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}