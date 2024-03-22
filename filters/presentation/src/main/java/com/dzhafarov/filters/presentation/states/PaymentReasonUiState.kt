package com.dzhafarov.filters.presentation.states

import com.dzhafarov.expense.presentation.PaymentReasonItem

data class PaymentReasonUiState(
    val reasons: List<PaymentReasonItem> = emptyList(),
    val selected: List<PaymentReasonItem> = emptyList(),
    val isExpanded: Boolean = false,
    val title: String = ""
)
