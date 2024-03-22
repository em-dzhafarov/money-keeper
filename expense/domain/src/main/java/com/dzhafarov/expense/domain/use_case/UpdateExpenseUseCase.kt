package com.dzhafarov.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.expense.domain.model.Expense
import com.dzhafarov.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class UpdateExpenseUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Expense, Unit> {

    override suspend fun execute(input: Expense) {
        repository.update(input)
    }
}
