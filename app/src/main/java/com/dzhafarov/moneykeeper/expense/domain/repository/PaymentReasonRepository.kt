package com.dzhafarov.moneykeeper.expense.domain.repository

import com.dzhafarov.moneykeeper.expense.domain.model.PaymentReason

interface PaymentReasonRepository {
    suspend fun getAll(): List<PaymentReason>
}