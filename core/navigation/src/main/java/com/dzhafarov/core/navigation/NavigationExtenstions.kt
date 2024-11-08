package com.dzhafarov.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun NavController.navigateTo(
    destination: Destination,
    args: List<Any?> = emptyList(),
    isRoot: Boolean = false
) {
    val nonNullArgs = args.filterNotNull()

    val route = if (nonNullArgs.isNotEmpty()) {
        destination.route + "/" + nonNullArgs.joinToString(separator = "/")
    } else {
        destination.route
    }

    navigate(route) {
        if (isRoot) {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
