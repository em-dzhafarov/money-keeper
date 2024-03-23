plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.profile.domain"
}

dependencies {
    implementProjects(
        AppProject.Core.Domain,
        AppProject.DateTime.Domain
    )

    hilt()
}