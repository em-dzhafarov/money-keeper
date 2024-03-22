package com.dzhafarov.moneykeeper.date_time.presentation.date

internal sealed class DateEvent {
    data object NavigateBack : DateEvent()
    data class Result(val value: Long) : DateEvent()
}
