package com.dzhafarov.settings.presentation

data class SettingsUiState(
    val title: String = "",
    val appearanceAndLookingLabel: String = "",
    val selectThemeLabel: String = "",
    val lightThemeLabel: String = "",
    val darkThemeLabel: String = "",
    val systemThemeLabel: String = "",
    val dynamicColorsLabel: String = "",
    val isLightTheme: Boolean = false,
    val isDarkTheme: Boolean = false,
    val isSystemTheme: Boolean = false,
    val isDynamicTheme: Boolean = false,
    val isDynamicColorsVisible: Boolean = false,
    val isThemeItemsVisible: Boolean = true,
    val isAppearanceSectionVisible: Boolean = true
)
