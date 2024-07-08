package com.dzhafarov.home.ui.events

import android.widget.Toast
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dzhafarov.core.navigation.Destination
import com.dzhafarov.core.navigation.navigateTo
import com.dzhafarov.core.ui.utils.collectAsEffect
import com.dzhafarov.home.presentation.HomeEvent
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ScreenEvents(
    events: Flow<HomeEvent>,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    events.collectAsEffect { event ->
        when (event) {
            is HomeEvent.AddExpense -> navController.openAddExpense()
            is HomeEvent.EditExpense -> navController.openEditExpense(event)
            is HomeEvent.OpenSettings -> navController.openSettings()
            is HomeEvent.OpenSearch -> navController.openSearch()
            is HomeEvent.OpenFilter -> navController.openFilter(event)
            is HomeEvent.OpenDashboard -> navController.openDashboard()
            is HomeEvent.DeleteExpense -> snackbarHostState.deleteExpense(event)
        }
    }
}

private fun NavController.openAddExpense() {
    navigateTo(
        destination = Destination.Screen.Expense,
        args = listOf(0L)
    )
}

private fun NavController.openEditExpense(event: HomeEvent.EditExpense) {
   navigateTo(
        destination = Destination.Screen.Expense,
        args = listOf(event.id)
    )
}

private suspend fun SnackbarHostState.deleteExpense(event: HomeEvent.DeleteExpense) {
    val result = showSnackbar(
        message = event.message,
        actionLabel = event.actionLabel,
        withDismissAction = true,
        duration = SnackbarDuration.Short
    )

    when (result) {
        SnackbarResult.ActionPerformed -> event.onActionPerformed()
        SnackbarResult.Dismissed -> event.onDismissed()
    }
}

private fun NavController.openSettings() {
    navigateTo(Destination.Screen.Settings)
}

private fun NavController.openSearch() {
    navigateTo(Destination.BottomSheet.Search)
}

private fun NavController.openDashboard() {
    navigateTo(Destination.Screen.Dashboard)
}

private fun NavController.openFilter(event: HomeEvent.OpenFilter) {
    if (event.hasExpenses) {
        navigateTo(Destination.BottomSheet.Filter)
    } else {
        Toast.makeText(context, event.emptyExpenses, Toast.LENGTH_SHORT).show()
    }
}