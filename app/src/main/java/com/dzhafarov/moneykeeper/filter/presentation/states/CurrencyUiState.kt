package com.dzhafarov.moneykeeper.filter.presentation.states

import com.dzhafarov.moneykeeper.expense.presentation.CurrencyItem

data class CurrencyUiState(
    val currencies: List<CurrencyItem> = emptyList(),
    val selected: List<CurrencyItem> = emptyList(),
    val isExpanded: Boolean = false,
    val title: String = ""
)
