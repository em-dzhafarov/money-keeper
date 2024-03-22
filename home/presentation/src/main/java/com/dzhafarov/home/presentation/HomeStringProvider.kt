package com.dzhafarov.home.presentation

interface HomeStringProvider {
    val title: String
    val noExpensesYet: String
    val noExpensesMatchTheGivenFilter: String
    val noExpensesToFilterOut: String
    val addExpense: String
    val editExpense: String
    val paidByPrefix: String
    val expenseDeletedMessage: String
    val undoDeleteButton: String
}
