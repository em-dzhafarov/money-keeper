sealed class AppProject(val name: String) {

    object Core : AppProject(":core") {
        object Data : AppProject("$name:data")
        object Domain : AppProject("$name:domain")
        object Presentation : AppProject("$name:presentation")
        object Navigation : AppProject("$name:navigation")
        object UI : AppProject("$name:ui")
    }

    object AboutApp : AppProject(":about_app") {
        object Presentation : AppProject("$name:presentation")
        object UI : AppProject("$name:ui")
        object DI : AppProject("$name:di")
    }

    object Dashboard : AppProject(":dashboard") {
        object UI : AppProject("$name:ui")
    }

    object DateTime : AppProject(":date_time") {
        object DI : AppProject("$name:di")
        object Domain : AppProject("$name:domain")
        object Presentation : AppProject("$name:presentation")
        object UI : AppProject("$name:ui")
    }

    object Expense : AppProject(":expense") {
        object DI : AppProject("$name:di")
        object Data : AppProject("$name:data")
        object Domain : AppProject("$name:domain")
        object Presentation : AppProject("$name:presentation")
        object UI : AppProject("$name:ui")
    }

    object Filters : AppProject(":filters") {
        object DI : AppProject("$name:di")
        object Data : AppProject("$name:data")
        object Domain : AppProject("$name:domain")
        object Presentation : AppProject("$name:presentation")
        object UI : AppProject("$name:ui")
    }

    object Home : AppProject(":home") {
        object DI : AppProject("$name:di")
        object Domain : AppProject("$name:domain")
        object Presentation : AppProject("$name:presentation")
        object UI : AppProject("$name:ui")
    }

    object Notifications : AppProject(":notifications") {
        object UI : AppProject("$name:ui")
    }

    object Profile : AppProject(":profile") {
        object DI : AppProject("$name:di")
        object Domain : AppProject("$name:domain")
        object UI : AppProject("$name:ui")
    }

    object Search : AppProject(":search") {
        object UI : AppProject("$name:ui")
    }

    object Settings : AppProject(":settings") {
        object DI : AppProject("$name:di")
        object Data : AppProject("$name:data")
        object Domain : AppProject("$name:domain")
        object Presentation : AppProject("$name:presentation")
        object UI : AppProject("$name:ui")
    }
}