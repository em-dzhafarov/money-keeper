package com.dzhafarov.expense.domain.repository

import com.dzhafarov.expense.domain.model.PaymentMethod

interface PaymentMethodRepository {
    suspend fun getAll(): List<PaymentMethod>
}