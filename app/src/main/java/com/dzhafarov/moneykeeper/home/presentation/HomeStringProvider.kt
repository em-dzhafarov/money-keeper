package com.dzhafarov.moneykeeper.home.presentation

interface HomeStringProvider {
    suspend fun welcome(userName: String): String
    suspend fun noExpensesYet(): String
}
