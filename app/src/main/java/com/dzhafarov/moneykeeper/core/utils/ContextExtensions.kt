package com.dzhafarov.moneykeeper.core.utils

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore("settings")

val Context.isDarkTheme: Boolean
    get() = resources.configuration.uiMode.let {
        (it and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }