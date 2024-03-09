package com.dzhafarov.moneykeeper.filter.presentation

interface FilterStringProvider {
    val applyFilters: String
    val clearFilters: String
    val cancel: String
    val priceRangeTitle: String
    val descriptionTitle: String
    val descriptionHint: String
    val currencyTitle: String
    val paymentMethodTitle: String
    val paymentReasonTitle: String
}