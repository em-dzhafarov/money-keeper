package com.dzhafarov.expense.domain.repository

import com.dzhafarov.expense.domain.model.Currency

interface CurrencyRepository {
    suspend fun getAll(): List<Currency>
}