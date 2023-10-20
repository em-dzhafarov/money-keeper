package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCase
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCase<Nothing?, List<Expense>> {

    override suspend fun execute(input: Nothing?): List<Expense> {
        return repository.getAll()
    }
}
