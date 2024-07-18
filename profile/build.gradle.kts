plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Profile}"
}

dependencies {
    apiProjects(
        AppProject.Profile.DI,
        AppProject.Profile.UI
    )
}