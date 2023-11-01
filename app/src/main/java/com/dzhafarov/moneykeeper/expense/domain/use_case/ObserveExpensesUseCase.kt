package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCase
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveExpensesUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCase<Nothing?, Flow<List<Expense>>> {

    override suspend fun execute(input: Nothing?): Flow<List<Expense>> {
        return repository.observeAll()
    }
}