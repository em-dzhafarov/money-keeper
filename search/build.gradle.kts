plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Search}"
}

dependencies {
    apiProject(AppProject.Search.UI)
}