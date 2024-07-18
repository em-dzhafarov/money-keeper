plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Notifications}"
}

dependencies {
    apiProject(AppProject.Notifications.UI)
}