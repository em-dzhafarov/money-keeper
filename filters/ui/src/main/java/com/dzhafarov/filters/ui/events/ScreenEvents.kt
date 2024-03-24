package com.dzhafarov.filters.ui.events

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dzhafarov.core.ui.utils.collectAsEffect
import com.dzhafarov.filters.presentation.FilterEvent
import kotlinx.coroutines.flow.Flow

@Composable
internal fun FilterEvents(
    events: Flow<FilterEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is FilterEvent.Dismiss -> navController.popBackStack()
        }
    }
}
