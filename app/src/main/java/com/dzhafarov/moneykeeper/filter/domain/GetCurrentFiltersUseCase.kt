package com.dzhafarov.moneykeeper.filter.domain

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.filter.domain.mapper.FilterDataMapper
import com.dzhafarov.moneykeeper.filter.presentation.FilterData
import com.dzhafarov.moneykeeper.filter.storage.CurrentFiltersPreference
import javax.inject.Inject

class GetCurrentFiltersUseCase @Inject constructor(
    private val preference: CurrentFiltersPreference,
    private val filterDataMapper: FilterDataMapper
) : UseCaseSuspend<Nothing?, FilterData?> {

    override suspend fun execute(input: Nothing?): FilterData? {
        return preference.get()?.let { filterDataMapper.from(it) }
    }
}
