plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Home.DI}"
}

dependencies {
    implementProjects(
        AppProject.Home.Domain,
        AppProject.Home.Presentation,
        AppProject.Home.UI
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}