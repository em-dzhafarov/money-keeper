package com.dzhafarov.moneykeeper.settings.di

import com.dzhafarov.moneykeeper.settings.presentation.SettingsStringProvider
import com.dzhafarov.moneykeeper.settings.presentation.SettingsStringProviderImpl
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