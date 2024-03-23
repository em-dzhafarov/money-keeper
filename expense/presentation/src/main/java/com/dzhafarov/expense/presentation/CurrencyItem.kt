package com.dzhafarov.expense.presentation

import androidx.annotation.DrawableRes
import com.dzhafarov.expense.domain.model.Currency

data class CurrencyItem(
    val value: Currency,
    @DrawableRes val iconRes: Int,
    val code: String,
    val name: String
) {
    override fun equals(other: Any?): Boolean {
        return value == (other as? CurrencyItem)?.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
