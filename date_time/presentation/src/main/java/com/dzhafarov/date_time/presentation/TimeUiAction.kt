package com.dzhafarov.date_time.presentation

sealed class TimeUiAction {
    data object OnDismiss : TimeUiAction()
    data class OnTimeSelected(val hour: Int, val minute: Int) : TimeUiAction()
}