package com.dzhafarov.filters.di

import com.dzhafarov.filters.data.di.FiltersDataModule
import com.dzhafarov.filters.ui.di.FiltersUiModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [FiltersDataModule::class, FiltersUiModule::class])
@InstallIn(SingletonComponent::class)
interface FilterModule