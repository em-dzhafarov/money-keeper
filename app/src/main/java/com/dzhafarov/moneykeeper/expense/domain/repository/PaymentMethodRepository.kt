package com.dzhafarov.moneykeeper.expense.domain.repository

import com.dzhafarov.moneykeeper.expense.domain.model.PaymentMethod

interface PaymentMethodRepository {
    suspend fun getAll(): List<PaymentMethod>
}