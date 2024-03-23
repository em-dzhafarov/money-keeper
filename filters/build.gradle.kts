plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.filters"
}

dependencies {
    apiProjects(
        AppProject.Filters.DI,
        AppProject.Filters.UI
    )
}