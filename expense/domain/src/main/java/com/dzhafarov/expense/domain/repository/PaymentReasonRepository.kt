package com.dzhafarov.expense.domain.repository

import com.dzhafarov.expense.domain.model.PaymentReason

interface PaymentReasonRepository {
    suspend fun getAll(): List<PaymentReason>
}