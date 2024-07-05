package com.dzhafarov.filters.ui.di

import com.dzhafarov.filters.presentation.FilterStringProvider
import com.dzhafarov.filters.ui.FilterStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [FiltersUiInternalModule::class])
@DisableInstallInCheck
interface FiltersUiModule

@Module
@DisableInstallInCheck
internal interface FiltersUiInternalModule {
    @Binds
    fun bindFilterStringProvider(actual: FilterStringProviderImpl): FilterStringProvider
}