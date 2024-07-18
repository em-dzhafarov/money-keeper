plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "${AppProject.Expense.Data}"
}

dependencies {
    implementProjects(
        AppProject.Core.Data,
        AppProject.Expense.Domain,
        AppProject.DateTime.Domain
    )

    implementation(libs.bundles.room)
    ksp(libs.room.ksp)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}