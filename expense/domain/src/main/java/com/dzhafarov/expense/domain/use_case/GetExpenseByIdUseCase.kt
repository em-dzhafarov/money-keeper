package com.dzhafarov.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.expense.domain.model.Expense
import com.dzhafarov.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class GetExpenseByIdUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Long, Expense?> {

    override suspend fun execute(input: Long): Expense? {
        return repository.findById(input)
    }
}