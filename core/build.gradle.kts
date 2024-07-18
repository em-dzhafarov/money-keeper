plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "${AppProject.Core}"
}

dependencies {
    apiProjects(
        AppProject.Core.UI,
        AppProject.Core.Navigation,
        AppProject.Core.Domain
    )
}
