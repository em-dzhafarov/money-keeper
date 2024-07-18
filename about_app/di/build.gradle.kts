plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.AboutApp.DI}"
}

dependencies {
    implementProjects(
        AppProject.AboutApp.Presentation,
        AppProject.AboutApp.UI
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}
