package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class SaveExpenseUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Expense, Unit> {

    override suspend fun execute(input: Expense) {
        repository.insert(input)
    }
}