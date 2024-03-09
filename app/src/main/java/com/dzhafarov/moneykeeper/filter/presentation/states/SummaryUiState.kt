package com.dzhafarov.moneykeeper.filter.presentation.states

data class SummaryUiState(
    val apply: String = "",
    val cancel: String = "",
    val clear: String = "",
    val isApplied: Boolean = false
)
