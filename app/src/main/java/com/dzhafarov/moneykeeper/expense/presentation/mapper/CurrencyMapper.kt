package com.dzhafarov.moneykeeper.expense.presentation.mapper

import android.content.Context
import com.dzhafarov.moneykeeper.R
import com.dzhafarov.moneykeeper.core.domain.mapper.OneWayMapper
import com.dzhafarov.moneykeeper.expense.domain.model.Currency
import com.dzhafarov.moneykeeper.expense.presentation.CurrencyItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : OneWayMapper<Currency, CurrencyItem> {

    override suspend fun map(input: Currency): CurrencyItem {
        val nameRes = when (input) {
            is Currency.PLN -> R.string.currency_pln
            is Currency.USD -> R.string.currency_usd
            is Currency.EUR -> R.string.currency_eur
        }

        return CurrencyItem(
            value = input,
            displayName = context.getString(nameRes)
        )
    }
}
