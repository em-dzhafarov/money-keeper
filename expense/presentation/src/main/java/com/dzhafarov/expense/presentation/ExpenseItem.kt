package com.dzhafarov.expense.presentation

data class ExpenseItem(
    val id: Long = 0,
    val amount: String,
    val method: PaymentMethodItem,
    val reason: PaymentReasonItem,
    val currency: CurrencyItem,
    val description: String,
    val time: String
)
