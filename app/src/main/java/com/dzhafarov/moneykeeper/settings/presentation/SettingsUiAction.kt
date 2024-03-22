package com.dzhafarov.moneykeeper.settings.presentation

internal sealed class SettingsUiAction {
    data object OnNavigateBack : SettingsUiAction()
    data object OnAppearanceClick : SettingsUiAction()
    data class OnDynamicThemeCheckChanged(val isChecked: Boolean) : SettingsUiAction()
    data object OnSelectThemeClick : SettingsUiAction()
    data object OnLightThemeSelected : SettingsUiAction()
    data object OnDarkThemeSelected : SettingsUiAction()
    data object OnSystemThemeSelected : SettingsUiAction()
}