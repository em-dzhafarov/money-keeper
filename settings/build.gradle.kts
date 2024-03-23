plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.settings"
}

dependencies {
    apiProjects(
        AppProject.Settings.DI,
        AppProject.Settings.Domain,
        AppProject.Settings.UI
    )
}