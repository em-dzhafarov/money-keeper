plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.dashboard"
}

dependencies {
    apiProject(AppProject.Dashboard.UI)
}