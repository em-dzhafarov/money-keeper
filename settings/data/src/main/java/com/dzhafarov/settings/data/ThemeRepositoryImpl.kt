package com.dzhafarov.settings.data

import com.dzhafarov.settings.data.mapper.ThemeMapper
import com.dzhafarov.settings.domain.ThemeRepository
import com.dzhafarov.settings.domain.model.Theme
import com.dzhafarov.settings.domain.model.ThemeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

internal class ThemeRepositoryImpl(
    private val themeTypePreference: ThemeTypePreference,
    private val isDynamicThemePreference: IsDynamicThemePreference,
    private val themeMapper: ThemeMapper
) : ThemeRepository {

    override fun observeSelectedTheme(): Flow<Theme> {
        return themeTypePreference.observe()
            .map(ThemeType::of)
            .combine(isDynamicThemePreference.observe(), ::Pair)
            .map(themeMapper::map)
    }

    override suspend fun getSelectedTheme(): Theme {
        val type = themeTypePreference.get()?.let(ThemeType::of)
        val isDynamic = isDynamicThemePreference.get()

        return themeMapper.map(type to isDynamic)
    }

    override suspend fun save(data: Theme) {
        themeTypePreference.edit(data.type.ordinal)
        isDynamicThemePreference.edit(data.isDynamic)
    }
}