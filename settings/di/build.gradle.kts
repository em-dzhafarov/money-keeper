plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.settings.di"
}

dependencies {
    implementProjects(
        AppProject.Settings.Presentation,
        AppProject.Settings.UI,
        AppProject.Settings.Data,
        AppProject.Settings.Domain
    )

    hilt()
}