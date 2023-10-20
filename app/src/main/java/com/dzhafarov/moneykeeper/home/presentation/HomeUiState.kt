package com.dzhafarov.moneykeeper.home.presentation

import com.dzhafarov.moneykeeper.expense.domain.model.Expense

data class HomeUiState(
    val welcomeMessage: String = "",
    val emptyExpensesMessage: String = "",
    val expenses: List<Expense> = emptyList()
)
