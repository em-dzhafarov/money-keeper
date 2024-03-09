package com.dzhafarov.moneykeeper.filter.presentation.states

data class DescriptionUiState(
    val title: String = "",
    val isExpanded: Boolean = false,
    val value: String = "",
    val hint: String = ""
)
