package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.model.Currency
import javax.inject.Inject

class GetDefaultCurrencyUseCase @Inject constructor() : UseCaseSuspend<Nothing?, Currency> {

    override suspend fun execute(input: Nothing?): Currency {
        return Currency.PLN
    }
}