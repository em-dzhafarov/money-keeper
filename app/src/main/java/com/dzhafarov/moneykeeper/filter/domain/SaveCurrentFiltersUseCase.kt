package com.dzhafarov.moneykeeper.filter.domain

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.filter.domain.mapper.FilterDataMapper
import com.dzhafarov.moneykeeper.filter.presentation.FilterData
import com.dzhafarov.moneykeeper.filter.storage.CurrentFiltersPreference
import javax.inject.Inject

class SaveCurrentFiltersUseCase @Inject constructor(
    private val preference: CurrentFiltersPreference,
    private val filterDataMapper: FilterDataMapper
) : UseCaseSuspend<FilterData, Unit> {

    override suspend fun execute(input: FilterData) {
         preference.edit(filterDataMapper.to(input))
    }
}
