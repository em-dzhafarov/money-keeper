package com.dzhafarov.moneykeeper.date_time.presentation

data class DateSelectorUiState(
    val current: Long = 0,
    val title: String = "",
    val confirm: String = "",
    val cancel: String = ""
)
