plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.search"
}

dependencies {
    apiProject(AppProject.Search.UI)
}