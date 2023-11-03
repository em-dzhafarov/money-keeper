package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCase
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class DeleteExpenseByIdUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCase<Long, Unit> {

    override suspend fun execute(input: Long) {
        repository.findById(input)?.let {
            repository.delete(it)
        }
    }
}