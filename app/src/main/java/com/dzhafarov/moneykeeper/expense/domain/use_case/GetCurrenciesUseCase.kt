package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.model.Currency
import com.dzhafarov.moneykeeper.expense.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : UseCaseSuspend<Nothing?, List<Currency>> {

    override suspend fun execute(input: Nothing?): List<Currency> {
        return currencyRepository.getAll()
    }
}