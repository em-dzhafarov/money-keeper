package com.dzhafarov.expense.domain.model

import com.dzhafarov.date_time.domain.Timestamp

data class Expense(
    val id: Long,
    val amount: Double,
    val method: PaymentMethod,
    val reason: PaymentReason,
    val currency: Currency,
    val description: String,
    val time: Timestamp
)
