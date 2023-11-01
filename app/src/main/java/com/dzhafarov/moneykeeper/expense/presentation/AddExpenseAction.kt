package com.dzhafarov.moneykeeper.expense.presentation

sealed class AddExpenseAction {
    object NavigateBack : AddExpenseAction()
    data class SelectDate(val millis: Long? = null) : AddExpenseAction()
    data class SelectTime(val hour: Int? = null, val minute: Int? = null) : AddExpenseAction()
    object ExpenseSaved : AddExpenseAction()
}
