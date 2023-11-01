package com.dzhafarov.moneykeeper.expense.domain.repository

import com.dzhafarov.moneykeeper.expense.domain.model.Currency

interface CurrencyRepository {
    suspend fun getAll(): List<Currency>
}