plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.date_time.ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    implementProjects(
        AppProject.Core.UI,
        AppProject.Core.Navigation,
        AppProject.Core.Presentation,
        AppProject.DateTime.Presentation
    )

    compose()
    navigation()
    hilt()
}