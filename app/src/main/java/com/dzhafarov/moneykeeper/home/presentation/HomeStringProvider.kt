package com.dzhafarov.moneykeeper.home.presentation

interface HomeStringProvider {
    suspend fun title(): String
    suspend fun welcome(userName: String): String
    suspend fun noExpensesYet(): String
    suspend fun noExpensesMatchTheGivenFilter(): String
    suspend fun noExpensesToFilterOut(): String
    suspend fun addExpense(): String
    suspend fun editExpense(): String
    suspend fun paidByPrefix(): String
    suspend fun expenseDeletedMessage(): String
    suspend fun undoDeleteButton(): String
}
