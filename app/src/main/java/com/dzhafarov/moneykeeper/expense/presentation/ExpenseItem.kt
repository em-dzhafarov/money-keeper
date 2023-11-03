package com.dzhafarov.moneykeeper.expense.presentation

import com.dzhafarov.moneykeeper.date_time.domain.Timestamp

data class ExpenseItem(
    val id: Long = 0,
    val amount: String,
    val method: PaymentMethodItem,
    val reason: PaymentReasonItem,
    val currency: CurrencyItem,
    val description: String,
    val time: Timestamp
)
