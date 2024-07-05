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
    implementProjects(
        AppProject.Core.Data,
        AppProject.Settings.Domain
    )

    hilt()
}