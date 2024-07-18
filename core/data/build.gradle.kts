plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Core.Data}"
}

dependencies {
    implementation(libs.datastore.preferences)
    implementation(libs.gson)
}