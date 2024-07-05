plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.settings.domain"
}

dependencies {
    implementProjects(
        AppProject.Core.Domain,
    )

    hilt()
}