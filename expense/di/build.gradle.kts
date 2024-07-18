plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Expense.DI}"
}

dependencies {
    implementProjects(
        AppProject.Expense.Data,
        AppProject.Expense.Domain,
        AppProject.Expense.Presentation,
        AppProject.Expense.UI
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}