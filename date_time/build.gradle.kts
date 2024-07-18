plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.DateTime}"
}

dependencies {
    apiProjects(
        AppProject.DateTime.DI,
        AppProject.DateTime.UI
    )
}