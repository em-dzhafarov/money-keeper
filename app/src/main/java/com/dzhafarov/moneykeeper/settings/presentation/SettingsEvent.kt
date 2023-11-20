package com.dzhafarov.moneykeeper.settings.presentation

sealed class SettingsEvent {
    object NavigateBack : SettingsEvent()
    object NavigateHome : SettingsEvent()
}
