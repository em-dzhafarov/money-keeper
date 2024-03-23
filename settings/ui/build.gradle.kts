plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.settings.ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    implementProjects(
        AppProject.Settings.Presentation,
        AppProject.Core.UI,
        AppProject.Core.Navigation,
        AppProject.Core.Presentation
    )

    compose()
    navigation()
    hilt()
}