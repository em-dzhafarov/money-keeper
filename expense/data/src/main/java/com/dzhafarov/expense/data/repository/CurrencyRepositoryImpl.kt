package com.dzhafarov.expense.data.repository

import com.dzhafarov.expense.domain.model.Currency
import com.dzhafarov.expense.domain.repository.CurrencyRepository
import javax.inject.Inject

internal class CurrencyRepositoryImpl @Inject constructor() : CurrencyRepository {

    override suspend fun getAll(): List<Currency> {
        return listOf(
            Currency.USD,
            Currency.PLN,
            Currency.EUR
        )
    }
}