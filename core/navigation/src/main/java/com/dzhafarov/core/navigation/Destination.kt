package com.dzhafarov.core.navigation

sealed class Destination(val route: String) {

    companion object {
        val initial = Screen.Home

        private val allDestinations: List<Destination> = listOf(
            Screen.Home,
            Screen.Dashboard,
            Screen.Profile,
            Screen.Settings,
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
        data object Home : Screen("home")
        data object Dashboard : Screen("dashboard")
        data object Profile : Screen("profile")
        data object Settings : Screen("settings")
        data object Expense : Screen("expense")
        data object Notifications : Screen("notifications")
    }
}
