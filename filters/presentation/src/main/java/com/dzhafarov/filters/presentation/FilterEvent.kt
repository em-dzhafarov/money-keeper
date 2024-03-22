package com.dzhafarov.filters.presentation

sealed class FilterEvent {
    data object Dismiss : FilterEvent()
}