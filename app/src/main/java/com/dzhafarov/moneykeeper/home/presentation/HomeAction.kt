package com.dzhafarov.moneykeeper.home.presentation

sealed class HomeAction {
    object AddExpense : HomeAction()
    object OpenNotifications : HomeAction()
    object OpenAboutAppInfo : HomeAction()
    data class EditExpense(val id: Long) : HomeAction()
    data class DeleteExpense(
        val message: String,
        val actionLabel: String,
        val onActionPerformed: () -> Unit,
        val onDismissed: suspend () -> Unit,
    ) : HomeAction()
}
