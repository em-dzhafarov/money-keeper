pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Money Keeper"
include(":app")

include(":about_app")
include(":about_app:ui")
include(":about_app:presentation")
include(":about_app:di")

include(":core:domain")
include(":core:presentation")
include(":core:ui")
include(":core:navigation")
