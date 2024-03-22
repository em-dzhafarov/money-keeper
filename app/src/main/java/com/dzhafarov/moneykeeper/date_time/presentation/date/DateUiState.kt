package com.dzhafarov.moneykeeper.date_time.presentation.date

internal data class DateUiState(
    val current: Long = 0,
    val title: String = "",
    val confirm: String = "",
    val cancel: String = ""
)
