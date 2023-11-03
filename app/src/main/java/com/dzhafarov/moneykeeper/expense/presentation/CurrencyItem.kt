package com.dzhafarov.moneykeeper.expense.presentation

import androidx.annotation.DrawableRes
import com.dzhafarov.moneykeeper.expense.domain.model.Currency

data class CurrencyItem(
    val value: Currency,
    @DrawableRes val iconRes: Int,
    val code: String,
)
