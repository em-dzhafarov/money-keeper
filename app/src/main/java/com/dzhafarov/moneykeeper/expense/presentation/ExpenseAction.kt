package com.dzhafarov.moneykeeper.expense.presentation

sealed class ExpenseAction {
    object NavigateBack : ExpenseAction()
    data class SelectDate(val millis: Long? = null) : ExpenseAction()
    data class SelectTime(val hour: Int? = null, val minute: Int? = null) : ExpenseAction()
    object ExpenseSaved : ExpenseAction()
    object ExpenseUpdated : ExpenseAction()
    object ExpenseDeleted : ExpenseAction()
}
