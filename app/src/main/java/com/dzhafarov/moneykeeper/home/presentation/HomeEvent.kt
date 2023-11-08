package com.dzhafarov.moneykeeper.home.presentation

sealed class HomeEvent {
    object AddExpense : HomeEvent()
    data class EditExpense(val id: Long) : HomeEvent()
    object OpenNotifications : HomeEvent()
    object OpenAboutAppInfo : HomeEvent()
    object OpenSearch : HomeEvent()
    object OpenFilter : HomeEvent()
    data class DeleteExpense(
        val message: String,
        val actionLabel: String,
        val onActionPerformed: () -> Unit,
        val onDismissed: suspend () -> Unit,
    ) : HomeEvent()
}
