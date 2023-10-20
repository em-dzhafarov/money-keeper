package com.dzhafarov.moneykeeper.expense.domain.model

sealed class PaymentReason {
    object Food : PaymentReason()
    object Health : PaymentReason()
    object Beauty : PaymentReason()
    object Transportation : PaymentReason()
    object Clothes : PaymentReason()
    object Accommodation : PaymentReason()
    object Subscription : PaymentReason()
    object Entertainment : PaymentReason()
    object Travel : PaymentReason()
    object Study : PaymentReason()
    object Tax : PaymentReason()
}
