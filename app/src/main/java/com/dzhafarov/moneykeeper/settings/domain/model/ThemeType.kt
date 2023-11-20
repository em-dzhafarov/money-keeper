package com.dzhafarov.moneykeeper.settings.domain.model

enum class ThemeType {
    LIGHT, DARK, SYSTEM;

    companion object {
        val Default = SYSTEM

        fun of(value: Int?): ThemeType? {
            return value?.let { values()[it] }
        }
    }
}