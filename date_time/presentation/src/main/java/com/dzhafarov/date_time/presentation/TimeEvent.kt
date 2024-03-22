package com.dzhafarov.date_time.presentation

sealed class TimeEvent {
    data object NavigateBack : TimeEvent()
    data class Result(val value: Pair<Int, Int>) : TimeEvent()
}
