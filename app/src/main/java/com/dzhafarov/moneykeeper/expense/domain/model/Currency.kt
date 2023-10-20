package com.dzhafarov.moneykeeper.expense.domain.model

sealed class Currency {
    object PLN : Currency()
    object USD : Currency()
    object EUR : Currency()
}
