package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCase
import com.dzhafarov.moneykeeper.expense.domain.model.Currency
import javax.inject.Inject

class GetDefaultCurrencyUseCase @Inject constructor() : UseCase<Nothing?, Currency> {

    override suspend fun execute(input: Nothing?): Currency {
        return Currency.PLN
    }
}