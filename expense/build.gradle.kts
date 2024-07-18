plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Expense}"
}

dependencies {
    apiProjects(
        AppProject.Expense.DI,
        AppProject.Expense.Data,
        AppProject.Expense.UI
    )
}