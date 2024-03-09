package com.dzhafarov.moneykeeper.filter.presentation.states

import com.dzhafarov.moneykeeper.date_time.domain.Timestamp

data class FilterUiState(
    val endDateTime: Timestamp? = null,
    val startDateTime: Timestamp? = null,

    val reason: PaymentReasonUiState = PaymentReasonUiState(),
    val method: PaymentMethodUiState = PaymentMethodUiState(),
    val currency: CurrencyUiState = CurrencyUiState(),
    val description: DescriptionUiState = DescriptionUiState(),
    val priceRange: PriceRangeUiState = PriceRangeUiState(),
    val summary: SummaryUiState = SummaryUiState()
)
