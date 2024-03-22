package com.dzhafarov.settings.domain.model

enum class ThemeType {
    LIGHT, DARK, SYSTEM;

    companion object {
        val Default = SYSTEM

        fun of(value: Int?): ThemeType? {
            return value?.let { entries[it] }
        }
    }
}