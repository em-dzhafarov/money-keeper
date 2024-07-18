plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.kapt)
}

android {
    namespace = "${AppProject.Expense.UI}"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementProjects(
        AppProject.Core.UI,
        AppProject.Core.Navigation,
        AppProject.Core.Presentation,
        AppProject.Expense.Presentation
    )

    implementation(platform(libs.compose))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.kapt)
}