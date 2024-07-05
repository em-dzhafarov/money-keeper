package com.dzhafarov.settings.data.di

import com.dzhafarov.settings.data.IsDynamicThemePreference
import com.dzhafarov.settings.data.ThemeRepositoryImpl
import com.dzhafarov.settings.data.ThemeTypePreference
import com.dzhafarov.settings.data.mapper.ThemeMapper
import com.dzhafarov.settings.domain.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [SettingsDataInternalModule::class])
@DisableInstallInCheck
interface SettingsDataModule

@Module
@DisableInstallInCheck
internal object SettingsDataInternalModule {

    @Provides
    fun provideThemeRepository(
        themeTypePreference: ThemeTypePreference,
        isDynamicThemePreference: IsDynamicThemePreference,
        themeMapper: ThemeMapper
    ): ThemeRepository {
        return ThemeRepositoryImpl(
            themeTypePreference,
            isDynamicThemePreference,
            themeMapper
        )
    }
}