package com.dzhafarov.core.navigation

sealed class Destination(val route: String) {

    companion object {
        val initial = Screen.Root.Home

        private val allDestinations: List<Destination> = listOf(
            Screen.Root.Home,
            Screen.Root.Dashboard,
            Screen.Root.Profile,
            Screen.Root.Settings,
            Screen.Expense,
            Screen.Notifications,
            Dialog.AboutApp,
            Dialog.DateSelector,
            Dialog.TimeSelector,
            BottomSheet.Filter,
            BottomSheet.Search,
        )

        fun isDialog(route: String?): Boolean {
            return of(route) is Dialog
        }

        fun isBottomSheet(route: String?): Boolean {
            return of(route) is BottomSheet
        }

        fun isRootScreen(route: String?): Boolean {
            return of(route) is Screen.Root
        }

        fun of(route: String?): Destination? {
            return allDestinations.find { route?.startsWith(it.route) ?: false }
        }
    }

    sealed class Dialog(route: String) : Destination(route) {
        data object AboutApp : Dialog("about_app")

        data object DateSelector : Dialog("date_selector")

        data object TimeSelector : Dialog("time_selector")
    }

    sealed class BottomSheet(route: String) : Destination(route) {
        data object Search : BottomSheet(route = "search")

        data object Filter : BottomSheet(route = "filter")
    }

    sealed class Screen(route: String) : Destination(route) {

        sealed class Root(route: String) : Screen(route) {
            data object Home : Root(route = "home")
            data object Dashboard : Root(route = "dashboard")
            data object Profile : Root(route = "profile")
            data object Settings : Root(route = "settings")
        }

        data object Expense : Screen(route = "expense")
        data object Notifications : Screen(route = "notifications")
    }
}
