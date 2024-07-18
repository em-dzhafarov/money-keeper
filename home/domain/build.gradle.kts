plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Home.Domain}"
}

dependencies {
    implementProjects(
        AppProject.Core.Domain,
        AppProject.Expense.Domain
    )

    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}