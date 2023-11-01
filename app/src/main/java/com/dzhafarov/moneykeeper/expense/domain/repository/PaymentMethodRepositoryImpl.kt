package com.dzhafarov.moneykeeper.expense.domain.repository

import com.dzhafarov.moneykeeper.expense.domain.model.PaymentMethod
import javax.inject.Inject

class PaymentMethodRepositoryImpl @Inject constructor() : PaymentMethodRepository {

    override suspend fun getAll(): List<PaymentMethod> {
        return listOf(
            PaymentMethod.CreditCard,
            PaymentMethod.Cash,
            PaymentMethod.Online
        )
    }
}