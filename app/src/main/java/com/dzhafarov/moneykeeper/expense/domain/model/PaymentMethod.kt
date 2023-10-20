package com.dzhafarov.moneykeeper.expense.domain.model

sealed class PaymentMethod {
    object Card : PaymentMethod()
    object Cash : PaymentMethod()
    object Online : PaymentMethod()
}
