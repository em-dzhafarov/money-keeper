package com.dzhafarov.moneykeeper.home.presentation

import com.dzhafarov.moneykeeper.expense.presentation.ExpenseItem

data class HomeUiState(
    val title: String = "",
    val welcomeMessage: String = "",
    val emptyExpensesMessage: String = "",
    val addExpenseMessage: String = "",
    val editExpenseLabel: String = "",
    val paidByPrefix: String = "",
    val expenses: List<ExpenseItem> = emptyList(),
    val displayMode: ExpensesDisplayMode = ExpensesDisplayMode.LIST
)
