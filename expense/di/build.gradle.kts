plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.expense.di"
}

dependencies {
    implementProjects(
        AppProject.Expense.Data,
        AppProject.Expense.Domain,
        AppProject.Expense.Presentation,
        AppProject.Expense.UI
    )

    hilt()
}