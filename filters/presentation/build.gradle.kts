plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Filters.Presentation}"
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

    implementation(libs.viewmodel)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}