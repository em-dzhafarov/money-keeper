plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.home"
}

dependencies {
    apiProjects(
        AppProject.Home.DI,
        AppProject.Home.UI
    )
}