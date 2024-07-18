plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.DateTime.Presentation}"
}

dependencies {
    implementProjects(
        AppProject.Core.Presentation,
        AppProject.DateTime.Domain
    )

    implementation(libs.viewmodel)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}