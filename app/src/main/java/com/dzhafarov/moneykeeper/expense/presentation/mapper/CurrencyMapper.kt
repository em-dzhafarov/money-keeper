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
        val (nameRes, iconRes) = when (input) {
            is Currency.PLN -> R.string.currency_pln to R.drawable.currency_zloty
            is Currency.USD -> R.string.currency_usd to R.drawable.currency_dollar
            is Currency.EUR -> R.string.currency_eur to R.drawable.currency_euro
        }

        return CurrencyItem(
            value = input,
            iconRes = iconRes,
            code = context.getString(nameRes)
        )
    }
}
