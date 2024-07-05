package com.dzhafarov.filters.domain

import com.dzhafarov.core.domain.use_case.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCurrentFiltersUseCase @Inject constructor(
    private val repository: FiltersRepository
) : UseCase<Nothing?, Flow<FilterData?>> {

    override fun execute(input: Nothing?): Flow<FilterData?> {
        return repository.observeAppliedFilters()
    }
}