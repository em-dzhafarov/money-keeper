plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.about_app.presentation"
}

dependencies {
    implementProject(AppProject.Core.Presentation)

    hilt()
    viewModel()
}