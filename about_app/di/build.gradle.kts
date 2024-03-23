plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.about_app.di"
}

dependencies {
    implementProject(AppProject.AboutApp.Presentation)
    implementProject(AppProject.AboutApp.UI)

    hilt()
}
