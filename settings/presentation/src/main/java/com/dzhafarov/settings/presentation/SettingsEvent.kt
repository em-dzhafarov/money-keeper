package com.dzhafarov.settings.presentation

sealed class SettingsEvent {
    data object NavigateBack : SettingsEvent()
    data object NavigateHome : SettingsEvent()
}
