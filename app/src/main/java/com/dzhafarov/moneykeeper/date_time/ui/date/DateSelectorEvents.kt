package com.dzhafarov.moneykeeper.date_time.ui.date

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dzhafarov.core.ui.utils.collectAsEffect
import com.dzhafarov.moneykeeper.date_time.presentation.date.DateEvent
import kotlinx.coroutines.flow.Flow

@Composable
internal fun DateSelectorEvents(
    events: Flow<DateEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is DateEvent.NavigateBack -> navController.popBackStack()
            is DateEvent.Result -> navController.setResult(event.value)
        }
    }
}

private fun NavController.setResult(value: Long) {
    previousBackStackEntry
        ?.savedStateHandle
        ?.set(DateSelector.SELECTED_DATE_RESULT, value)

    popBackStack()
}