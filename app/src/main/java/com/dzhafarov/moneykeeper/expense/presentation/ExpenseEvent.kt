package com.dzhafarov.moneykeeper.expense.presentation

sealed class ExpenseEvent {
    data object NavigateBack : ExpenseEvent()
    data class SelectDate(val millis: Long? = null) : ExpenseEvent()
    data class SelectTime(val hour: Int? = null, val minute: Int? = null) : ExpenseEvent()
    data object ExpenseSaved : ExpenseEvent()
    data object ExpenseUpdated : ExpenseEvent()
    data object ExpenseDeleted : ExpenseEvent()
}
