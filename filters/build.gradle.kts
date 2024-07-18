plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Filters}"
}

dependencies {
    apiProjects(
        AppProject.Filters.DI,
        AppProject.Filters.UI
    )
}