plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.filters.presentation"
}

dependencies {
    implementProjects(
        AppProject.Core.Domain,
        AppProject.Core.Presentation,
        AppProject.Expense.Domain,
        AppProject.Expense.Presentation,
        AppProject.DateTime.Domain,
        AppProject.Filters.Domain
    )

    viewModel()
    hilt()
}