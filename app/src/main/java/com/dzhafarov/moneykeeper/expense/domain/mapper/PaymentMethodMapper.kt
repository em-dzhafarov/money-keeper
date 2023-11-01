package com.dzhafarov.moneykeeper.expense.domain.mapper

import com.dzhafarov.moneykeeper.core.domain.mapper.Mapper
import com.dzhafarov.moneykeeper.expense.db.dto.PaymentMethodDTO
import com.dzhafarov.moneykeeper.expense.domain.model.PaymentMethod
import javax.inject.Inject

class PaymentMethodMapper @Inject constructor() : Mapper<PaymentMethodDTO, PaymentMethod> {

    override suspend fun to(input: PaymentMethodDTO): PaymentMethod {
        return when (input) {
            PaymentMethodDTO.CARD -> PaymentMethod.CreditCard
            PaymentMethodDTO.CASH -> PaymentMethod.Cash
            PaymentMethodDTO.ONLINE -> PaymentMethod.Online
        }
    }

    override suspend fun from(input: PaymentMethod): PaymentMethodDTO {
        return when (input) {
            is PaymentMethod.CreditCard -> PaymentMethodDTO.CARD
            is PaymentMethod.Cash -> PaymentMethodDTO.CASH
            is PaymentMethod.Online -> PaymentMethodDTO.ONLINE
        }
    }
}