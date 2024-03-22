package com.dzhafarov.date_time.ui.time

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dzhafarov.core.ui.utils.collectAsEffect
import com.dzhafarov.date_time.presentation.TimeEvent
import kotlinx.coroutines.flow.Flow

@Composable
internal fun TimeSelectorEvents(
    events: Flow<TimeEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is TimeEvent.NavigateBack -> navController.popBackStack()
            is TimeEvent.Result -> navController.setResult(event.value)
        }
    }
}

private fun NavController.setResult(value: Pair<Int, Int>) {
    previousBackStackEntry
        ?.savedStateHandle
        ?.set(TimeSelector.SELECTED_TIME_RESULT, value)

    popBackStack()
}