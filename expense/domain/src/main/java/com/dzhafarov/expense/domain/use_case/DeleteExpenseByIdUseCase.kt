package com.dzhafarov.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class DeleteExpenseByIdUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Long, Unit> {

    override suspend fun execute(input: Long) {
        repository.findById(input)?.let {
            repository.delete(it)
        }
    }
}