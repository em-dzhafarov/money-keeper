plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.home.domain"
}

dependencies {
    implementProjects(
        AppProject.Core.Domain,
        AppProject.Expense.Domain
    )

    hilt()
}