package com.dzhafarov.settings.di

import com.dzhafarov.settings.data.di.SettingsDataModule
import com.dzhafarov.settings.ui.di.SettingsUiModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [SettingsDataModule::class, SettingsUiModule::class])
@InstallIn(SingletonComponent::class)
interface SettingsModule