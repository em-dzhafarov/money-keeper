package com.dzhafarov.moneykeeper.date_time.presentation

sealed class DateSelectorUiAction {
    object NavigateBack : DateSelectorUiAction()
    data class Result(val value: Long) : DateSelectorUiAction()
}
