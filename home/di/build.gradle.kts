plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.home.di"
}

dependencies {
    implementProjects(
        AppProject.Home.Domain,
        AppProject.Home.Presentation,
        AppProject.Home.UI
    )

    hilt()
}