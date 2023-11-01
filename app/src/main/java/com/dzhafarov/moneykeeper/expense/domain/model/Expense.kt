package com.dzhafarov.moneykeeper.expense.domain.model

import java.time.LocalDateTime

data class Expense(
    val id: Long = 0,
    val amount: Double,
    val method: PaymentMethod,
    val reason: PaymentReason,
    val currency: Currency,
    val description: String,
    val time: LocalDateTime
)
