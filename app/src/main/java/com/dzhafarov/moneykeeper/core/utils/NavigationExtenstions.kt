package com.dzhafarov.moneykeeper.core.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.dzhafarov.moneykeeper.core.ui.Screen

fun NavController.navigateTo(screen: Screen) {
    navigate(screen.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
