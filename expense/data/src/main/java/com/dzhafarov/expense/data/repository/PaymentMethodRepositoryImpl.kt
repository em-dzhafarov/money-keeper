package com.dzhafarov.expense.data.repository

import com.dzhafarov.expense.domain.model.PaymentMethod
import com.dzhafarov.expense.domain.repository.PaymentMethodRepository
import javax.inject.Inject

internal class PaymentMethodRepositoryImpl @Inject constructor() : PaymentMethodRepository {

    override suspend fun getAll(): List<PaymentMethod> {
        return listOf(
            PaymentMethod.CreditCard,
            PaymentMethod.Cash,
            PaymentMethod.Online
        )
    }
}