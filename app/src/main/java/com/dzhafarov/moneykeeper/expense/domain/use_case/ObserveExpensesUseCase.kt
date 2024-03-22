package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveExpensesUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Nothing?, Flow<List<Expense>>> {

    override suspend fun execute(input: Nothing?): Flow<List<Expense>> {
        return repository.observeAll()
    }
}