package com.dzhafarov.moneykeeper.date_time.presentation

sealed class DateSelectorEvent {
    object NavigateBack : DateSelectorEvent()
    data class Result(val value: Long) : DateSelectorEvent()
}
