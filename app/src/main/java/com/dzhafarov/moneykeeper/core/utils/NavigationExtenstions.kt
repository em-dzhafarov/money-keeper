package com.dzhafarov.moneykeeper.core.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.dzhafarov.moneykeeper.core.ui.Destination

fun NavController.navigateTo(destination: Destination, isRoot: Boolean = false) {
    navigate(destination.route) {
        if (isRoot) {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
