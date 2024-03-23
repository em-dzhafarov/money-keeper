plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.profile"
}

dependencies {
    apiProjects(
        AppProject.Profile.DI,
        AppProject.Profile.UI
    )
}