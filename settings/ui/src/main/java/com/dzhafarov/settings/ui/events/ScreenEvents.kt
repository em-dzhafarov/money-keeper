package com.dzhafarov.settings.ui.events

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dzhafarov.core.navigation.Destination
import com.dzhafarov.core.ui.utils.collectAsEffect
import com.dzhafarov.settings.presentation.SettingsEvent
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ScreenEvents(
    events: Flow<SettingsEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is SettingsEvent.NavigateBack -> navController.popBackStack()
            is SettingsEvent.NavigateHome -> navController.navigateHome()
        }
    }
}

private fun NavController.navigateHome() {
    popBackStack(
        route = Destination.Screen.Home.route,
        inclusive = false
    )
}