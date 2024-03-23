plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.filters.ui"

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
        AppProject.Filters.Presentation,
        AppProject.Expense.Presentation
    )

    compose()
    navigation()
    hilt()
}