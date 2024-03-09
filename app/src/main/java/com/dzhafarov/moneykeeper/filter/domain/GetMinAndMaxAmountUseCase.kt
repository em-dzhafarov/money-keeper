package com.dzhafarov.moneykeeper.filter.domain

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import javax.inject.Inject

class GetMinAndMaxAmountUseCase @Inject constructor(
    private val repository: ExpensesRepository
) : UseCaseSuspend<Nothing?, Pair<Double, Double>> {

    override suspend fun execute(input: Nothing?): Pair<Double, Double> {
        return (repository.getMinAmount() to repository.getMaxAmount())
    }
}