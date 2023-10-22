package com.dzhafarov.moneykeeper.home.presentation

import com.dzhafarov.moneykeeper.expense.domain.model.Expense

data class HomeUiState(
    val title: String = "",
    val welcomeMessage: String = "",
    val emptyExpensesMessage: String = "",
    val addExpenseMessage: String = "",
    val expenses: List<Expense> = emptyList()
)
