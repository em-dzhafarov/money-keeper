package com.dzhafarov.expense.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dzhafarov.core.navigation.Destination
import com.dzhafarov.core.navigation.navigateTo
import com.dzhafarov.core.ui.utils.collectAsEffect
import com.dzhafarov.expense.presentation.ExpenseEvent
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ExpenseEvents(
    events: Flow<ExpenseEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is ExpenseEvent.NavigateBack -> navController.popBackStack()
            is ExpenseEvent.SelectDate -> navController.selectDate(event)
            is ExpenseEvent.SelectTime -> navController.selectTime(event)
            is ExpenseEvent.ExpenseSaved -> navController.popBackStack()
            is ExpenseEvent.ExpenseUpdated -> navController.popBackStack()
            is ExpenseEvent.ExpenseDeleted -> navController.popBackStack()
        }
    }
}

private fun NavController.selectDate(event: ExpenseEvent.SelectDate) {
    navigateTo(
        destination = Destination.Dialog.DateSelector,
        args = listOf(event.millis)
    )
}

private fun NavController.selectTime(event: ExpenseEvent.SelectTime) {
    navigateTo(
        destination = Destination.Dialog.TimeSelector,
        args = listOf(event.hour, event.minute)
    )
}