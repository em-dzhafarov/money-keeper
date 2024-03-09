package com.dzhafarov.moneykeeper.filter.presentation.states

import com.dzhafarov.moneykeeper.expense.presentation.PaymentReasonItem

data class PaymentReasonUiState(
    val reasons: List<PaymentReasonItem> = emptyList(),
    val selected: List<PaymentReasonItem> = emptyList(),
    val isExpanded: Boolean = false,
    val title: String = ""
)
