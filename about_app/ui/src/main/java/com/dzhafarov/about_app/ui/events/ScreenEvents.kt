package com.dzhafarov.about_app.ui.events

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dzhafarov.about_app.presentation.AboutAppEvent
import com.dzhafarov.core.ui.utils.collectAsEffect
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ScreenEvents(
    events: Flow<AboutAppEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is AboutAppEvent.Close -> navController.popBackStack()
        }
    }
}
