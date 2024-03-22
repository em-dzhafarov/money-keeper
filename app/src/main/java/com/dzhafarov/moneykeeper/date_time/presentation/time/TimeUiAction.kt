package com.dzhafarov.moneykeeper.date_time.presentation.time

internal sealed class TimeUiAction {
    data object OnDismiss : TimeUiAction()
    data class OnTimeSelected(val hour: Int, val minute: Int) : TimeUiAction()
}