package com.dzhafarov.moneykeeper.expense.ui.presentation

interface AddExpenseStringProvider {
    suspend fun title(): String
}