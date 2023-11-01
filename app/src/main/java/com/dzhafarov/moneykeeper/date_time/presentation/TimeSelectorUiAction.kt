package com.dzhafarov.moneykeeper.date_time.presentation

sealed class TimeSelectorUiAction {
    object NavigateBack : TimeSelectorUiAction()
    data class Result(val value: Pair<Int, Int>) : TimeSelectorUiAction()
}
