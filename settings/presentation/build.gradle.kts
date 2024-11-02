plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Settings.Presentation}"
}

dependencies {
    implementProjects(
        AppProject.Core.Presentation,
        AppProject.Core.Domain,
        AppProject.Settings.Domain
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}