package com.dzhafarov.moneykeeper.date_time.presentation.date

internal sealed class DateUiAction {
    data object OnDismiss : DateUiAction()
    data class OnDateSelected(val millis: Long) : DateUiAction()
}
