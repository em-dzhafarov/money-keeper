package com.dzhafarov.moneykeeper.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dzhafarov.moneykeeper.about.ui.AboutAppDialog
import com.dzhafarov.moneykeeper.core.presentation.MainViewModel
import com.dzhafarov.moneykeeper.core.utils.navigateTo
import com.dzhafarov.moneykeeper.dashboard.DashboardScreen
import com.dzhafarov.moneykeeper.date_time.ui.DateSelector
import com.dzhafarov.moneykeeper.date_time.ui.TimeSelector
import com.dzhafarov.moneykeeper.expense.ui.ExpenseScreen
import com.dzhafarov.moneykeeper.home.ui.HomeScreen
import com.dzhafarov.moneykeeper.notifications.NotificationsScreen
import com.dzhafarov.moneykeeper.profile.ProfileScreen
import com.dzhafarov.moneykeeper.settings.SettingsScreen
import com.dzhafarov.moneykeeper.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { viewModel.isSplashShown }

        setContent {
            AppTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    val shouldBlur = Destination.isDialog(route)

    Scaffold(
        modifier = Modifier.blur(if (shouldBlur) 2.dp else 0.dp),
        bottomBar = {
            BottomNavContainer(navController)
        }
    ) { padding ->
        ContentNavContainer(
            navController,
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

        Destination.isDialog(route).not() -> {
            shouldShowBottomNavigation = false
        }
    }

    if (shouldShowBottomNavigation) {
        NavigationBar {
            Destination.roots.forEach { screen ->
                NavHostItem(
                    isSelected = navBackStackEntry
                        ?.destination
                        ?.hierarchy
                        ?.any { it.route == screen.route } == true,
                    screen = screen,
                    onClick = {
                        navController.navigateTo(screen, isRoot = true)
                    }
                )
            }
        }
    }
}

@Composable
private fun ContentNavContainer(
    navController: NavHostController,
    modifier: Modifier = Modifier
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
            content = { AboutAppDialog(navController) }
        )

        dialog(
            route = Destination.Dialog.DateSelector.route + "/{${DateSelector.SELECTED_DATE_ARG}}",
            arguments = listOf(
                navArgument(DateSelector.SELECTED_DATE_ARG) {
                    type = NavType.LongType
                }
            ),
            content = { entry ->
                DateSelector(
                    navController = navController,
                    initial = entry.arguments?.getLong(DateSelector.SELECTED_DATE_ARG)
                )
            }
        )

        dialog(
            route = Destination.Dialog.TimeSelector.route
                    + "/{${TimeSelector.SELECTED_HOUR_ARG}}"
                    + "/{${TimeSelector.SELECTED_MINUTE_ARG}}",
            arguments = listOf(
                navArgument(TimeSelector.SELECTED_HOUR_ARG) {
                    type = NavType.IntType
                },
                navArgument(TimeSelector.SELECTED_MINUTE_ARG) {
                    type = NavType.IntType
                }
            ),
            content = { entry ->
                TimeSelector(
                    navController = navController,
                    hours = entry.arguments?.getInt(TimeSelector.SELECTED_HOUR_ARG),
                    minutes = entry.arguments?.getInt(TimeSelector.SELECTED_MINUTE_ARG)
                )
            }
        )
    }
}

@Composable
private fun RowScope.NavHostItem(
    isSelected: Boolean,
    screen: Destination.Screen.Root,
    onClick: () -> Unit
) {
    val label = stringResource(screen.resourceId)

    NavigationBarItem(
        icon = {
            val vector = if (isSelected) {
                screen.selectedIcon
            } else {
                screen.unselectedIcon
            }

            Icon(vector, contentDescription = label)
        },
        label = { Text(label) },
        selected = isSelected,
        onClick = onClick
    )
}
