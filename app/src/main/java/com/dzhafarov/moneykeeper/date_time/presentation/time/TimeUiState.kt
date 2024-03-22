package com.dzhafarov.moneykeeper.date_time.presentation.time

internal data class TimeUiState(
    val title: String = "",
    val confirm: String = "",
    val cancel: String = "",
    val hour: Int = 0,
    val minute: Int = 0,
)
