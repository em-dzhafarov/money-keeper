package com.dzhafarov.home.presentation

sealed class HomeEvent {
    data object AddExpense : HomeEvent()
    data class EditExpense(val id: Long) : HomeEvent()
    data object OpenSettings : HomeEvent()
    data object OpenSearch : HomeEvent()
    data object OpenDashboard : HomeEvent()
    data class OpenFilter(
        val hasExpenses: Boolean,
        val emptyExpenses: String
    ) : HomeEvent()
    data class DeleteExpense(
        val message: String,
        val actionLabel: String,
        val onActionPerformed: () -> Unit,
        val onDismissed: suspend () -> Unit,
    ) : HomeEvent()
}
