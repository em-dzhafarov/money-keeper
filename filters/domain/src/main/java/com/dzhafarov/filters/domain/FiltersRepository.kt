package com.dzhafarov.filters.domain

import kotlinx.coroutines.flow.Flow

interface FiltersRepository {
    fun observeAppliedFilters(): Flow<FilterData?>
    suspend fun getAppliedFilters(): FilterData?
    suspend fun save(data: FilterData)
}