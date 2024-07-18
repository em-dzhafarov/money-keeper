plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.DateTime.DI}"
}

dependencies {
    implementProjects(
        AppProject.DateTime.Presentation,
        AppProject.DateTime.UI
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}