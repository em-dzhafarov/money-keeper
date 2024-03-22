package com.dzhafarov.date_time.presentation

sealed class DateEvent {
    data object NavigateBack : DateEvent()
    data class Result(val value: Long) : DateEvent()
}
