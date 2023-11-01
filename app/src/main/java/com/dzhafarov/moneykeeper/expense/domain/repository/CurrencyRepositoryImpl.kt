package com.dzhafarov.moneykeeper.expense.domain.repository

import com.dzhafarov.moneykeeper.expense.domain.model.Currency
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor() : CurrencyRepository {

    override suspend fun getAll(): List<Currency> {
        return listOf(
            Currency.USD,
            Currency.PLN,
            Currency.EUR
        )
    }
}