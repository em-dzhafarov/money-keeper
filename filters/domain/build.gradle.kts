plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = "${Config.namespace}.filters.domain"
}

dependencies {
    implementProjects(
        AppProject.Core.Data,
        AppProject.Core.Domain,
        AppProject.Filters.Data,
        AppProject.Expense.Domain,
        AppProject.DateTime.Domain
    )

    hilt()
}