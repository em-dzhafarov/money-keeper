package com.dzhafarov.filters.data

import com.dzhafarov.filters.data.mapper.FilterDataMapper
import com.dzhafarov.filters.domain.FilterData
import com.dzhafarov.filters.domain.FiltersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FiltersRepositoryImpl(
    private val preference: CurrentFiltersPreference,
    private val filterDataMapper: FilterDataMapper
) : FiltersRepository {

    override fun observeAppliedFilters(): Flow<FilterData?> {
        return preference.observe()
            .map { filterDataMapper.from(it.orEmpty()) }
    }

    override suspend fun getAppliedFilters(): FilterData? {
        return preference.get()?.let { filterDataMapper.from(it) }
    }

    override suspend fun save(data: FilterData) {
        preference.edit(filterDataMapper.to(data))
    }
}