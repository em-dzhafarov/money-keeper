package com.dzhafarov.expense.data.mapper

import com.dzhafarov.core.data.mapper.DTOMapper
import com.dzhafarov.expense.data.db.PaymentMethodDTO
import com.dzhafarov.expense.domain.model.PaymentMethod
import javax.inject.Inject

internal class PaymentMethodMapper @Inject constructor() : DTOMapper<PaymentMethodDTO, PaymentMethod> {

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