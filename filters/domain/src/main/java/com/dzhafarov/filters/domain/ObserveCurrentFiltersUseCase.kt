package com.dzhafarov.filters.domain

import com.dzhafarov.core.domain.use_case.UseCase
import com.dzhafarov.filters.data.CurrentFiltersPreference
import com.dzhafarov.filters.domain.mapper.FilterDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveCurrentFiltersUseCase @Inject constructor(
    private val preference: CurrentFiltersPreference,
    private val filterDataMapper: FilterDataMapper
) : UseCase<Nothing?, Flow<FilterData?>> {

    override fun execute(input: Nothing?): Flow<FilterData?> {
        return preference.observe()
            .map { filterDataMapper.from(it.orEmpty()) }
    }
}