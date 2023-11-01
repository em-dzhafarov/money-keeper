package com.dzhafarov.moneykeeper.expense.domain.model

sealed class PaymentMethod {
    object CreditCard : PaymentMethod()
    object Cash : PaymentMethod()
    object Online : PaymentMethod()
}
