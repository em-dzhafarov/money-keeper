package com.dzhafarov.moneykeeper.expense.presentation

sealed class ExpenseEvent {
    object NavigateBack : ExpenseEvent()
    data class SelectDate(val millis: Long? = null) : ExpenseEvent()
    data class SelectTime(val hour: Int? = null, val minute: Int? = null) : ExpenseEvent()
    object ExpenseSaved : ExpenseEvent()
    object ExpenseUpdated : ExpenseEvent()
    object ExpenseDeleted : ExpenseEvent()
}
