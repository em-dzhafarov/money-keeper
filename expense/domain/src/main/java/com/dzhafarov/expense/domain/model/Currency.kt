package com.dzhafarov.expense.domain.model

sealed class Currency {
    data object PLN : Currency()
    data object USD : Currency()
    data object EUR : Currency()
}
