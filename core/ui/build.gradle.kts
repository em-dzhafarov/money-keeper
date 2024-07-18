plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Core.UI}"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.lifecycle)
    implementation(platform(libs.compose))
    implementation(libs.bundles.compose)
    implementation(libs.accompanist.ui)
    implementation(libs.accompanist.nav)
}