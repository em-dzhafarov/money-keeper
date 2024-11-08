@file:OptIn(ExperimentalMaterialNavigationApi::class)

package com.dzhafarov.moneykeeper.main.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dzhafarov.about_app.ui.AboutAppDialog
import com.dzhafarov.core.navigation.Destination
import com.dzhafarov.core.navigation.Keys
import com.dzhafarov.dashboard.ui.DashboardScreen
import com.dzhafarov.date_time.ui.date.DateSelectorDialog
import com.dzhafarov.date_time.ui.time.TimeSelectorDialog
import com.dzhafarov.expense.ui.ExpenseScreen
import com.dzhafarov.filters.ui.FilterScreen
import com.dzhafarov.home.ui.HomeScreen
import com.dzhafarov.notifications.ui.NotificationsScreen
import com.dzhafarov.profile.ui.ProfileScreen
import com.dzhafarov.search.ui.SearchScreen
import com.dzhafarov.settings.ui.SettingsScreen
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
    ) { padding ->
        ContentNavContainer(
            navController,
            bottomSheetNavigator,
            Modifier.padding(padding)
        )
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
                route = Destination.Screen.Home.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { HomeScreen(navController) }
            )
            composable(
                route = Destination.Screen.Dashboard.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { DashboardScreen(navController) }
            )
            composable(
                route = Destination.Screen.Profile.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { ProfileScreen(navController) }
            )
            composable(
                route = Destination.Screen.Settings.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                content = { SettingsScreen(navController) }
            )

            composable(
                route = Destination.Screen.Expense.route
                        + "/{${Keys.Expense.SELECTED_EXPENSE_ID_ARG}}",
                arguments = listOf(
                    navArgument(Keys.Expense.SELECTED_EXPENSE_ID_ARG) {
                        type = NavType.LongType
                    }
                ),
                enterTransition = {
                    slideInVertically { it }
                },
                exitTransition = {
                   slideOutVertically { it }
                },
                content = { entry ->
                    ExpenseScreen(
                        navController = navController,
                        expenseId = entry.arguments?.getLong(Keys.Expense.SELECTED_EXPENSE_ID_ARG) ?: 0
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
                content = { AboutAppDialog(navController) }
            )

            dialog(
                route = Destination.Dialog.DateSelector.route + "/{${Keys.DateSelector.SELECTED_DATE_ARG}}",
                arguments = listOf(
                    navArgument(Keys.DateSelector.SELECTED_DATE_ARG) {
                        type = NavType.LongType
                    }
                ),
                content = { entry ->
                    DateSelectorDialog(
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
                    TimeSelectorDialog(
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
