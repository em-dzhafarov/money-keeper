package com.dzhafarov.date_time.presentation

sealed class DateUiAction {
    data object OnDismiss : DateUiAction()
    data class OnDateSelected(val millis: Long) : DateUiAction()
}
