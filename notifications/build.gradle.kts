plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.notifications"
}

dependencies {
    apiProject(AppProject.Notifications.UI)
}