package com.dzhafarov.filters.domain

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.filters.data.CurrentFiltersPreference
import com.dzhafarov.filters.domain.mapper.FilterDataMapper
import javax.inject.Inject

class GetCurrentFiltersUseCase @Inject constructor(
    private val preference: CurrentFiltersPreference,
    private val filterDataMapper: FilterDataMapper
) : UseCaseSuspend<Nothing?, FilterData?> {

    override suspend fun execute(input: Nothing?): FilterData? {
        return preference.get()?.let { filterDataMapper.from(it) }
    }
}
