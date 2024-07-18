plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.AboutApp}"
}

dependencies {
    apiProjects(
        AppProject.AboutApp.UI,
        AppProject.AboutApp.DI
    )
}