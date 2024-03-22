package com.dzhafarov.settings.di

import com.dzhafarov.settings.presentation.SettingsStringProvider
import com.dzhafarov.settings.ui.SettingsStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SettingsModule {

    @Binds
    fun bindSettingsStringProvider(actual: SettingsStringProviderImpl): SettingsStringProvider
}