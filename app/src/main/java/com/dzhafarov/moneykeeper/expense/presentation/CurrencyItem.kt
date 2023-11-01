package com.dzhafarov.moneykeeper.expense.presentation

import com.dzhafarov.moneykeeper.expense.domain.model.Currency

data class CurrencyItem(
    val value: Currency,
    val displayName: String = ""
)
