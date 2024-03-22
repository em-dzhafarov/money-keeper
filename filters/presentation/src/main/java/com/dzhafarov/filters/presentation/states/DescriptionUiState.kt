package com.dzhafarov.filters.presentation.states

data class DescriptionUiState(
    val title: String = "",
    val isExpanded: Boolean = false,
    val value: String = "",
    val hint: String = ""
)
