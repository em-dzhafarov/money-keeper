package com.dzhafarov.moneykeeper.date_time.presentation

sealed class TimeSelectorEvent {
    object NavigateBack : TimeSelectorEvent()
    data class Result(val value: Pair<Int, Int>) : TimeSelectorEvent()
}
