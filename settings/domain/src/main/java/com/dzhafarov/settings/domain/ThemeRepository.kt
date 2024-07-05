package com.dzhafarov.settings.domain

import com.dzhafarov.settings.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun observeSelectedTheme(): Flow<Theme>
    suspend fun getSelectedTheme(): Theme
    suspend fun save(data: Theme)
}