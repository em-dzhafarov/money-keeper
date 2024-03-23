plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.date_time"
}

dependencies {
    apiProjects(
        AppProject.DateTime.DI,
        AppProject.DateTime.UI
    )
}