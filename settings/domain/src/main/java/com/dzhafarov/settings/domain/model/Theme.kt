package com.dzhafarov.settings.domain.model

import android.os.Build

data class Theme(val type: ThemeType, val isDynamic: Boolean) {

    companion object {
        val Default = Theme(
            type = ThemeType.Default,
            isDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        )
    }
}
