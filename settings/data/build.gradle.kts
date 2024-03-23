plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.settings.data"
}

dependencies {
    implementProject(AppProject.Core.Data)

    hilt()
}