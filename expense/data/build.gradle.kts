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
    implementProjects(
        AppProject.Core.Data,
        AppProject.Expense.Domain,
        AppProject.DateTime.Domain
    )

    room()
    hilt()
}