package com.dzhafarov.moneykeeper.settings.presentation

internal sealed class SettingsEvent {
    data object NavigateBack : SettingsEvent()
    data object NavigateHome : SettingsEvent()
}
