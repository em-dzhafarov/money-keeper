package com.dzhafarov.moneykeeper.date_time.presentation.time

internal sealed class TimeEvent {
    data object NavigateBack : TimeEvent()
    data class Result(val value: Pair<Int, Int>) : TimeEvent()
}
