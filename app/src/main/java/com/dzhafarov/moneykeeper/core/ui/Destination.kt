package com.dzhafarov.moneykeeper.core.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.dzhafarov.moneykeeper.R

sealed class Destination(val route: String) {

    companion object {
        val initial = Screen.Root.Home

        val roots = listOf(
            Screen.Root.Home,
            Screen.Root.Dashboard,
            Screen.Root.Profile,
            Screen.Root.Settings
        )

        private val allDestinations: List<Destination> = listOf(
            Screen.Root.Home,
            Screen.Root.Dashboard,
            Screen.Root.Profile,
            Screen.Root.Settings,
            Screen.Expense,
            Screen.Notifications,
            Dialog.AboutApp,
            Dialog.DateSelector,
            Dialog.TimeSelector
        )

        fun isDialog(route: String?): Boolean {
            return of(route) is Dialog
        }

        fun isRootScreen(route: String?): Boolean {
            return of(route) is Screen.Root
        }

        fun of(route: String?): Destination? {
            return allDestinations.find { route?.startsWith(it.route) ?: false }
        }
    }

    sealed class Dialog(route: String) : Destination(route) {
        object AboutApp : Dialog("about_app")

        object DateSelector : Dialog("date_selector")

        object TimeSelector : Dialog("time_selector")
    }

    sealed class Screen(
        route: String,
        @StringRes val resourceId: Int,
    ) : Destination(route) {

        sealed class Root(
            route: String,
            resourceId: Int,
            val selectedIcon: ImageVector,
            val unselectedIcon: ImageVector
        ) : Screen(route, resourceId) {

            object Home : Root(
                route = "home",
                resourceId = R.string.home_screen,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )

            object Dashboard : Root(
                route = "dashboard",
                resourceId = R.string.dashboard_screen,
                selectedIcon = Icons.Filled.DateRange,
                unselectedIcon = Icons.Outlined.DateRange
            )

            object Profile : Root(
                route = "profile",
                resourceId = R.string.profile_screen,
                selectedIcon = Icons.Filled.AccountCircle,
                unselectedIcon = Icons.Outlined.AccountCircle
            )

            object Settings : Root(
                route = "settings",
                resourceId = R.string.settings_screen,
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            )
        }

        object Expense : Screen(
            route = "expense",
            resourceId = R.string.add_expense_screen
        )

        object Notifications : Screen(
            route = "notifications",
            resourceId = R.string.notifications_screen
        )
    }
}
