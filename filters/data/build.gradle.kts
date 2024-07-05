plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.filters.data"
}

dependencies {
    implementProjects(
        AppProject.Core.Data,
        AppProject.Filters.Domain
    )

    hilt()
}