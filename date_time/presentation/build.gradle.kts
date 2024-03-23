plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.date_time.presentation"
}

dependencies {
    implementProjects(
        AppProject.Core.Presentation,
        AppProject.DateTime.Domain
    )

    viewModel()
    hilt()
}