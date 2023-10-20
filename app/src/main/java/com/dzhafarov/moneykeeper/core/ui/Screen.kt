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

val ROOT_SCREENS: List<Screen.Root> = listOf(
    Screen.Root.Home,
    Screen.Root.Dashboard,
    Screen.Root.Profile,
    Screen.Root.Settings
)

sealed class Screen(val route: String) {

    sealed class Root(
        route: String,
        @StringRes val resourceId: Int,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector
    ) : Screen(route) {

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
}
