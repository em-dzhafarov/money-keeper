package com.dzhafarov.settings.ui.di

import com.dzhafarov.settings.presentation.SettingsStringProvider
import com.dzhafarov.settings.ui.SettingsStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [SettingsUiInternalModule::class])
@DisableInstallInCheck
interface SettingsUiModule

@Module
@DisableInstallInCheck
internal interface SettingsUiInternalModule {

    @Binds
    fun bindSettingsStringProvider(actual: SettingsStringProviderImpl): SettingsStringProvider
}