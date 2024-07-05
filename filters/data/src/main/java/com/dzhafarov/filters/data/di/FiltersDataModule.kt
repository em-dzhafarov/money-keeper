package com.dzhafarov.filters.data.di

import com.dzhafarov.filters.data.CurrentFiltersPreference
import com.dzhafarov.filters.data.FiltersRepositoryImpl
import com.dzhafarov.filters.data.mapper.FilterDataMapper
import com.dzhafarov.filters.domain.FiltersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [FiltersDataInternalModule::class])
@DisableInstallInCheck
interface FiltersDataModule

@Module
@DisableInstallInCheck
internal object FiltersDataInternalModule {

    @Provides
    fun provideFiltersRepository(
        currentFiltersPreference: CurrentFiltersPreference,
        filterDataMapper: FilterDataMapper
    ): FiltersRepository {
        return FiltersRepositoryImpl(currentFiltersPreference, filterDataMapper)
    }
}