package com.dzhafarov.moneykeeper.expense.presentation.mapper

import android.content.Context
import com.dzhafarov.moneykeeper.R
import com.dzhafarov.core.domain.mapper.OneWayMapper
import com.dzhafarov.moneykeeper.expense.domain.model.Currency
import com.dzhafarov.moneykeeper.expense.presentation.CurrencyItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : OneWayMapper<Currency, CurrencyItem> {

    override suspend fun map(input: Currency): CurrencyItem {
        return when (input) {
            is Currency.PLN -> {
                CurrencyItem(
                    value = input,
                    iconRes = R.drawable.currency_zloty,
                    code = context.getString(R.string.currency_pln),
                    name = context.getString(R.string.currency_pln_full)
                )
            }
            is Currency.USD -> {
                CurrencyItem(
                    value = input,
                    iconRes = R.drawable.currency_dollar,
                    code = context.getString(R.string.currency_usd),
                    name = context.getString(R.string.currency_usd_full)
                )
            }
            is Currency.EUR -> {
                CurrencyItem(
                    value = input,
                    iconRes = R.drawable.currency_euro,
                    code = context.getString(R.string.currency_eur),
                    name = context.getString(R.string.currency_eur_full)
                )
            }
        }
    }
}
