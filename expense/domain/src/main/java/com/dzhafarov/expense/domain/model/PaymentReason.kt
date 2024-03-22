package com.dzhafarov.expense.domain.model

sealed class PaymentReason {
    data object Food : PaymentReason()
    data object Health : PaymentReason()
    data object Beauty : PaymentReason()
    data object Transportation : PaymentReason()
    data object Clothes : PaymentReason()
    data object Accommodation : PaymentReason()
    data object Subscription : PaymentReason()
    data object Entertainment : PaymentReason()
    data object Travel : PaymentReason()
    data object Study : PaymentReason()
    data object Tax : PaymentReason()
}
