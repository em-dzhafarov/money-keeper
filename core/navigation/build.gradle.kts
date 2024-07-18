plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Core.Navigation}"
}

dependencies {
    implementation(libs.bundles.navigation)
}
