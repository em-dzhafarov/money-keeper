package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class GetExpenseByIdUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Long, Expense?> {

    override suspend fun execute(input: Long): Expense? {
        return repository.findById(input)
    }
}