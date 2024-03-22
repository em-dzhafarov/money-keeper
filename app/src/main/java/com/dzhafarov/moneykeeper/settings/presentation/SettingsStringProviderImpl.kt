package com.dzhafarov.moneykeeper.settings.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class SettingsStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsStringProvider {

    override val title: String
        get() = context.getString(R.string.settings_screen)

    override val appearanceAndLookingLabel: String
        get() = context.getString(R.string.settings_screen_appearance_label)

    override val selectThemeLabel: String
        get() = context.getString(R.string.settings_screen_select_theme_label)

    override val lightThemeLabel: String
        get() = context.getString(R.string.settings_screen_light_theme)

    override val darkThemeLabel: String
        get() = context.getString(R.string.settings_screen_dark_theme)

    override val systemThemeLabel: String
        get() = context.getString(R.string.settings_screen_system_theme)

    override val dynamicColorsLabel: String
        get() = context.getString(R.string.settings_screen_dynamic_theme)
}
