plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.expense.presentation"
}

dependencies {
    implementProjects(
        AppProject.Core.Presentation,
        AppProject.Core.Domain,
        AppProject.Expense.Domain,
        AppProject.DateTime.Domain
    )

    viewModel()
    hilt()
}