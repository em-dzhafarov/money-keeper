package com.dzhafarov.filters.domain

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import javax.inject.Inject

class GetCurrentFiltersUseCase @Inject constructor(
    private val repository: FiltersRepository
) : UseCaseSuspend<Nothing?, FilterData?> {

    override suspend fun execute(input: Nothing?): FilterData? {
        return repository.getAppliedFilters()
    }
}
