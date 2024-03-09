package com.dzhafarov.moneykeeper.filter.presentation

sealed class FilterEvent {
    data object Dismiss : FilterEvent()
}