package com.dzhafarov.moneykeeper.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dzhafarov.moneykeeper.dashboard.DashboardScreen
import com.dzhafarov.moneykeeper.core.utils.navigateTo
import com.dzhafarov.moneykeeper.home.ui.HomeScreen
import com.dzhafarov.moneykeeper.profile.ProfileScreen
import com.dzhafarov.moneykeeper.settings.SettingsScreen
import com.dzhafarov.moneykeeper.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavContainer(navController)
        }
    ) { padding ->
        ContentNavContainer(navController, Modifier.padding(padding))
    }
}

@Composable
private fun BottomNavContainer(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val shouldShowBottomNavigation = navBackStackEntry
        ?.destination
        ?.hierarchy
        ?.any { destination -> destination.route in ROOT_SCREENS.map { it.route } } == true

    if (shouldShowBottomNavigation) {
        NavigationBar {
            ROOT_SCREENS.forEach { screen ->
                NavHostItem(
                    isSelected = navBackStackEntry
                        ?.destination
                        ?.hierarchy
                        ?.any { it.route == screen.route } == true,
                    screen = screen,
                    onClick = {
                        navController.navigateTo(screen)
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
        startDestination = Screen.Root.Home.route,
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        composable(Screen.Root.Home.route) { HomeScreen(navController) }
        composable(Screen.Root.Dashboard.route) { DashboardScreen(navController) }
        composable(Screen.Root.Profile.route) { ProfileScreen(navController) }
        composable(Screen.Root.Settings.route) { SettingsScreen(navController) }
    }
}

@Composable
private fun RowScope.NavHostItem(
    isSelected: Boolean,
    screen: Screen.Root,
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
