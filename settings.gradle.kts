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

include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":core:ui")
include(":core:navigation")

include(":about_app")
include(":about_app:ui")
include(":about_app:presentation")
include(":about_app:di")

include(":date_time")
include(":date_time:ui")
include(":date_time:presentation")
include(":date_time:di")
include(":date_time:domain")

include(":settings")
include(":settings:di")
include(":settings:presentation")
include(":settings:ui")
include(":settings:domain")
include(":settings:data")

include(":home")
include(":home:di")
include(":home:presentation")
include(":home:ui")
include(":home:domain")

include(":expense")
include(":expense:di")
include(":expense:data")
include(":expense:domain")
include(":expense:presentation")
include(":expense:ui")

include(":filters")
include(":filters:di")
include(":filters:domain")
include(":filters:presentation")
include(":filters:data")
include(":filters:ui")

include(":dashboard")
include(":dashboard:ui")

include(":notifications")
include(":notifications:ui")

include(":search")
include(":search:ui")

include(":profile")
include(":profile:ui")
include(":profile:domain")
include(":profile:di")
