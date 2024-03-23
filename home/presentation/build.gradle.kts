plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.home.presentation"
}

dependencies {
    implementProjects(
        AppProject.Core.Presentation,
        AppProject.Core.Domain,
        AppProject.Expense.Presentation,
        AppProject.Expense.Domain,
        AppProject.Filters.Presentation,
        AppProject.Filters.Domain,
        AppProject.Home.Domain
    )

    viewModel()
    hilt()
}