plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Expense.Presentation}"
}

dependencies {
    implementProjects(
        AppProject.Core.Presentation,
        AppProject.Core.Domain,
        AppProject.Expense.Domain,
        AppProject.DateTime.Domain
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}