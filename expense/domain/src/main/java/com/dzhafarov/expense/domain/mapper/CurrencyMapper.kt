package com.dzhafarov.expense.domain.mapper

import com.dzhafarov.core.domain.mapper.Mapper
import com.dzhafarov.expense.data.dto.CurrencyDTO
import com.dzhafarov.expense.domain.model.Currency
import javax.inject.Inject

class CurrencyMapper @Inject constructor() : Mapper<CurrencyDTO, Currency> {

    override suspend fun to(input: CurrencyDTO): Currency {
        return when (input) {
            CurrencyDTO.PLN -> Currency.PLN
            CurrencyDTO.USD -> Currency.USD
            CurrencyDTO.EUR -> Currency.EUR
        }
    }

    override suspend fun from(input: Currency): CurrencyDTO {
        return when (input) {
            is Currency.PLN -> CurrencyDTO.PLN
            is Currency.USD -> CurrencyDTO.USD
            is Currency.EUR -> CurrencyDTO.EUR
        }
    }
}
