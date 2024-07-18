plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Dashboard}"
}

dependencies {
    apiProject(AppProject.Dashboard.UI)
}