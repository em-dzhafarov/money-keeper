@file:OptIn(ExperimentalMaterialNavigationApi::class)

package com.dzhafarov.moneykeeper

import com.dzhafarov.core.navigation.Destination
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dzhafarov.core.navigation.Keys
import com.dzhafarov.moneykeeper.dashboard.DashboardScreen
import com.dzhafarov.expense.ui.ExpenseScreen
import com.dzhafarov.home.ui.HomeScreen
import com.dzhafarov.moneykeeper.notifications.NotificationsScreen
import com.dzhafarov.moneykeeper.profile.ProfileScreen
import com.dzhafarov.moneykeeper.search.SearchScreen
import com.dzhafarov.settings.ui.SettingsScreen
import com.dzhafarov.core.navigation.navigateTo
import com.dzhafarov.filters.ui.FilterScreen
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

@Composable
fun NavigationGraph() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    val shouldBlur = Destination.isDialog(route) || Destination.isBottomSheet(route)

    Scaffold(
        modifier = Modifier.blur(if (shouldBlur) 2.dp else 0.dp),
        bottomBar = {
            BottomNavContainer(navController)
        }
    ) { padding ->
        ContentNavContainer(
            navController,
            bottomSheetNavigator,
            Modifier.padding(padding)
        )
    }
}

@Composable
private fun BottomNavContainer(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    var shouldShowBottomNavigation by remember { mutableStateOf(false) }

    when {
        Destination.isRootScreen(route) -> {
            shouldShowBottomNavigation = true
        }

        Destination.isDialog(route).not() && Destination.isBottomSheet(route).not() -> {
            shouldShowBottomNavigation = false
        }
    }

    if (shouldShowBottomNavigation) {
        NavigationBar {
            NavHostItem(
                navController = navController,
                screen = Destination.Screen.Root.Home,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                label = stringResource(R.string.home_screen)
            )
            NavHostItem(
                navController = navController,
                screen = Destination.Screen.Root.Dashboard,
                selectedIcon = Icons.Filled.DateRange,
                unselectedIcon = Icons.Outlined.DateRange,
                label = stringResource(R.string.dashboard_screen)
            )
            NavHostItem(
                navController = navController,
                screen = Destination.Screen.Root.Profile,
                selectedIcon = Icons.Filled.AccountCircle,
                unselectedIcon = Icons.Outlined.AccountCircle,
                label = stringResource(R.string.profile_screen)
            )
            NavHostItem(
                navController = navController,
                screen = Destination.Screen.Root.Settings,
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                label = stringResource(R.string.settings_screen)
            )
        }
    }
}

@Composable
private fun ContentNavContainer(
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    modifier: Modifier = Modifier
) {
    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        scrimColor = Color.Transparent
    ) {
        NavHost(
            navController = navController,
            startDestination = Destination.initial.route,
            modifier = modifier
        ) {
            composable(
                route = Destination.Screen.Root.Home.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { HomeScreen(navController) }
            )
            composable(
                route = Destination.Screen.Root.Dashboard.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { DashboardScreen(navController) }
            )
            composable(
                route = Destination.Screen.Root.Profile.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { ProfileScreen(navController) }
            )
            composable(
                route = Destination.Screen.Root.Settings.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { SettingsScreen(navController) }
            )

            composable(
                route = Destination.Screen.Expense.route
                        + "/{${ExpenseScreen.SELECTED_EXPENSE_ID_ARG}}",
                arguments = listOf(
                    navArgument(ExpenseScreen.SELECTED_EXPENSE_ID_ARG) {
                        type = NavType.LongType
                    }
                ),
                enterTransition = {
                    scaleIn(
                        transformOrigin = TransformOrigin(1f, 1f)
                    )
                },
                exitTransition = {
                    scaleOut(
                        transformOrigin = TransformOrigin(1f, 1f)
                    )
                },
                content = { entry ->
                    ExpenseScreen(
                        navController = navController,
                        expenseId = entry.arguments?.getLong(ExpenseScreen.SELECTED_EXPENSE_ID_ARG) ?: 0
                    )
                }
            )

            composable(
                route = Destination.Screen.Notifications.route,
                enterTransition = {
                    scaleIn(
                        transformOrigin = TransformOrigin(1f, 0f)
                    )
                },
                exitTransition = {
                    scaleOut(
                        transformOrigin = TransformOrigin(1f, 0f)
                    )
                },
                content = {
                    NotificationsScreen(navController)
                }
            )

            dialog(
                route = Destination.Dialog.AboutApp.route,
                content = { com.dzhafarov.about_app.ui.AboutAppDialog(navController) }
            )

            dialog(
                route = Destination.Dialog.DateSelector.route + "/{${Keys.DateSelector.SELECTED_DATE_ARG}}",
                arguments = listOf(
                    navArgument(Keys.DateSelector.SELECTED_DATE_ARG) {
                        type = NavType.LongType
                    }
                ),
                content = { entry ->
                    com.dzhafarov.date_time.ui.date.DateSelectorDialog(
                        navController = navController,
                        initial = entry.arguments?.getLong(Keys.DateSelector.SELECTED_DATE_ARG)
                    )
                }
            )

            dialog(
                route = Destination.Dialog.TimeSelector.route
                        + "/{${Keys.TimeSelector.SELECTED_HOUR_ARG}}"
                        + "/{${Keys.TimeSelector.SELECTED_MINUTE_ARG}}",
                arguments = listOf(
                    navArgument(Keys.TimeSelector.SELECTED_HOUR_ARG) {
                        type = NavType.IntType
                    },
                    navArgument(Keys.TimeSelector.SELECTED_MINUTE_ARG) {
                        type = NavType.IntType
                    }
                ),
                content = { entry ->
                    com.dzhafarov.date_time.ui.time.TimeSelectorDialog(
                        navController = navController,
                        hours = entry.arguments?.getInt(Keys.TimeSelector.SELECTED_HOUR_ARG),
                        minutes = entry.arguments?.getInt(Keys.TimeSelector.SELECTED_MINUTE_ARG)
                    )
                }
            )

            bottomSheet(
                route = Destination.BottomSheet.Filter.route,
                content = {
                    FilterScreen(navController = navController)
                }
            )

            bottomSheet(
                route = Destination.BottomSheet.Search.route,
                content = {
                    SearchScreen(navController = navController)
                }
            )
        }
    }
}

@Composable
private fun RowScope.NavHostItem(
    navController: NavController,
    screen: Destination.Screen.Root,
    label: String,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val isSelected = navBackStackEntry
        ?.destination
        ?.hierarchy
        ?.any { it.route == screen.route } == true

    NavigationBarItem(
        icon = {
            val vector = if (isSelected) {
                selectedIcon
            } else {
                unselectedIcon
            }

            Icon(vector, contentDescription = label)
        },
        label = { Text(label) },
        selected = isSelected,
        onClick = { navController.navigateTo(screen, isRoot = true) }
    )
}
