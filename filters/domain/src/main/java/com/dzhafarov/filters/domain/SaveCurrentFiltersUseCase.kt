package com.dzhafarov.filters.domain

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import javax.inject.Inject

class SaveCurrentFiltersUseCase @Inject constructor(
    private val repository: FiltersRepository
) : UseCaseSuspend<FilterData, Unit> {

    override suspend fun execute(input: FilterData) {
        repository.save(input)
    }
}
