plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Filters.Data}"
}

dependencies {
    implementProjects(
        AppProject.Core.Data,
        AppProject.Filters.Domain
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}