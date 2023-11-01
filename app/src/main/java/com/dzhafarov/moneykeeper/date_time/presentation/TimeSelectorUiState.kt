package com.dzhafarov.moneykeeper.date_time.presentation

data class TimeSelectorUiState(
    val title: String = "",
    val confirm: String = "",
    val cancel: String = "",
    val hour: Int = 0,
    val minute: Int = 0,
)
