package com.dzhafarov.moneykeeper.filter.presentation

data class FilterData(
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val currencyCodes: Set<String>? = null,
    val paymentMethods: Set<String>? = null,
    val paymentReasons: Set<String>? = null,
    val description: String? = null,
    val start: Long? = null,
    val end: Long? = null
)
