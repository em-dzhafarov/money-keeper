package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Nothing?, List<Expense>> {

    override suspend fun execute(input: Nothing?): List<Expense> {
        return repository.getAll()
    }
}
