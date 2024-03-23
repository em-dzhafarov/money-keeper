plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.settings.domain"
}

dependencies {
    implementProjects(
        AppProject.Core.Data,
        AppProject.Core.Domain,
        AppProject.Settings.Data
    )

    hilt()
}