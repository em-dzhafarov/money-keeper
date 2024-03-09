package com.dzhafarov.moneykeeper.filter.presentation.states

data class PriceRangeUiState(
    val title: String = "",
    val isExpanded: Boolean = false,
    val initialMinAmount: Double = 0.0,
    val initialMaxAmount: Double = 0.0,
    val minAmount: Double? = null,
    val maxAmount: Double? = null
)
