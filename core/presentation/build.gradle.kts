plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Core.Presentation}"
}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.viewmodel)
}