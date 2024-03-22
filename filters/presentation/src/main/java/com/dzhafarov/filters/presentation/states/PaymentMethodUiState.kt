package com.dzhafarov.filters.presentation.states

import com.dzhafarov.expense.presentation.PaymentMethodItem

data class PaymentMethodUiState(
    val methods: List<PaymentMethodItem> = emptyList(),
    val selected: List<PaymentMethodItem> = emptyList(),
    val isExpanded: Boolean = false,
    val title: String = ""
)
