plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${Config.namespace}.core"
}

dependencies {
    apiProjects(
        AppProject.Core.UI,
        AppProject.Core.Navigation,
        AppProject.Core.Domain
    )
}
