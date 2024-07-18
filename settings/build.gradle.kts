plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Settings}"
}

dependencies {
    apiProjects(
        AppProject.Settings.DI,
        AppProject.Settings.Domain,
        AppProject.Settings.UI
    )
}