package com.dzhafarov.core.ui.utils

import android.content.Context
import android.content.res.Configuration

val Context.isDarkTheme: Boolean
    get() = resources.configuration.uiMode.let {
        (it and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }