plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.date_time.di"
}

dependencies {
    implementProjects(
        AppProject.DateTime.Presentation,
        AppProject.DateTime.UI
    )

    hilt()
}