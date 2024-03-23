plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.ksp)
}

android {
    namespace = "${Config.namespace}.expense.data"
}

dependencies {
    implementProject(AppProject.Core.Data)

    room()
    hilt()
}