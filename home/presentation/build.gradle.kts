plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Home.Presentation}"
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

    implementation(libs.viewmodel)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}