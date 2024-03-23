plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.filters.di"
}

dependencies {
    implementProjects(
        AppProject.Filters.Data,
        AppProject.Filters.Domain,
        AppProject.Filters.Presentation,
        AppProject.Filters.UI
    )

    hilt()
}