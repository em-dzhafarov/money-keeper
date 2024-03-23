plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.settings.presentation"
}

dependencies {
    implementProjects(
        AppProject.Core.Presentation,
        AppProject.Core.Domain,
        AppProject.Settings.Domain
    )

    viewModel()
    hilt()
}