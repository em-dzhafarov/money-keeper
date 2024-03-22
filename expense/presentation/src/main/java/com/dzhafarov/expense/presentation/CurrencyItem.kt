package com.dzhafarov.expense.presentation

import androidx.annotation.DrawableRes
import com.dzhafarov.expense.domain.model.Currency

data class CurrencyItem(
    val value: Currency,
    @DrawableRes val iconRes: Int,
    val code: String,
    val name: String
)
