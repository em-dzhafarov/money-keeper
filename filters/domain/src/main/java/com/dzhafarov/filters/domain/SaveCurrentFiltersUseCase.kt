package com.dzhafarov.filters.domain

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.filters.data.CurrentFiltersPreference
import com.dzhafarov.filters.domain.mapper.FilterDataMapper
import javax.inject.Inject

class SaveCurrentFiltersUseCase @Inject constructor(
    private val preference: CurrentFiltersPreference,
    private val filterDataMapper: FilterDataMapper
) : UseCaseSuspend<FilterData, Unit> {

    override suspend fun execute(input: FilterData) {
         preference.edit(filterDataMapper.to(input))
    }
}
