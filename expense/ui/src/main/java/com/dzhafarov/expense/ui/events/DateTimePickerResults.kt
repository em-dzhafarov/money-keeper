package com.dzhafarov.expense.ui.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.dzhafarov.core.navigation.Keys
import com.dzhafarov.expense.presentation.ExpenseUiAction

@Composable
internal fun DateTimePickerResults(
    navController: NavController,
    defaultDate: Long,
    defaultTime: Pair<Int, Int>,
    onAction: (ExpenseUiAction) -> Unit
) {
    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.takeIf { it.contains(Keys.DateSelector.SELECTED_DATE_RESULT) }
        ?.getStateFlow(Keys.DateSelector.SELECTED_DATE_RESULT, defaultDate)
        ?.collectAsState()
        ?.value
        ?.let { onAction(ExpenseUiAction.OnDateSelected(it)) }

    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.takeIf { it.contains(Keys.TimeSelector.SELECTED_TIME_RESULT) }
        ?.getStateFlow(Keys.TimeSelector.SELECTED_TIME_RESULT, defaultTime)
        ?.collectAsState()
        ?.value
        ?.let { onAction(ExpenseUiAction.OnTimeSelected(it)) }
}

