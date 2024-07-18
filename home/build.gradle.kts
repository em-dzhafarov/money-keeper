plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Home}"
}

dependencies {
    apiProjects(
        AppProject.Home.DI,
        AppProject.Home.UI
    )
}