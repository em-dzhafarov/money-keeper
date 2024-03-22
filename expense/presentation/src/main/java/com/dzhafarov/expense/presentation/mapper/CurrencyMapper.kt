package com.dzhafarov.expense.presentation.mapper

import com.dzhafarov.core.domain.mapper.OneWayMapper
import com.dzhafarov.expense.domain.model.Currency
import com.dzhafarov.expense.presentation.CurrencyItem
import com.dzhafarov.expense.presentation.DrawableResourceProvider
import com.dzhafarov.expense.presentation.ExpenseStringProvider
import javax.inject.Inject

class CurrencyMapper @Inject constructor(
    private val stringProvider: ExpenseStringProvider,
    private val drawableResourceProvider: DrawableResourceProvider
) : OneWayMapper<Currency, CurrencyItem> {

    override suspend fun map(input: Currency): CurrencyItem {
        return when (input) {
            is Currency.PLN -> {
                CurrencyItem(
                    value = input,
                    iconRes = drawableResourceProvider.currencyPln,
                    code = stringProvider.plnCurrencyName(false),
                    name = stringProvider.plnCurrencyName(true)
                )
            }
            is Currency.USD -> {
                CurrencyItem(
                    value = input,
                    iconRes = drawableResourceProvider.currencyUsd,
                    code = stringProvider.usdCurrencyName(false),
                    name = stringProvider.usdCurrencyName(true)
                )
            }
            is Currency.EUR -> {
                CurrencyItem(
                    value = input,
                    iconRes = drawableResourceProvider.currencyEur,
                    code = stringProvider.euroCurrencyName(false),
                    name = stringProvider.euroCurrencyName(true)
                )
            }
        }
    }
}
