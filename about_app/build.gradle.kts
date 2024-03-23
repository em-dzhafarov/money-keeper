plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.about_app"
}

dependencies {
    apiProject(AppProject.AboutApp.UI)
    apiProject(AppProject.AboutApp.DI)
}