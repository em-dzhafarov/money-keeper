package com.dzhafarov.expense.domain.model

sealed class PaymentMethod {
    data object CreditCard : PaymentMethod()
    data object Cash : PaymentMethod()
    data object Online : PaymentMethod()
}
