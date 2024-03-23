plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.expense"
}

dependencies {
    apiProjects(
        AppProject.Expense.DI,
        AppProject.Expense.Data,
        AppProject.Expense.UI
    )
}