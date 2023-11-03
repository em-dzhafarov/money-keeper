package com.dzhafarov.moneykeeper.expense.domain.model

import java.time.LocalDateTime

data class Expense(
    val id: Long,
    val amount: Double,
    val method: PaymentMethod,
    val reason: PaymentReason,
    val currency: Currency,
    val description: String,
    val time: LocalDateTime
)
