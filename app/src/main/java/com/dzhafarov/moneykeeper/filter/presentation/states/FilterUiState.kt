package com.dzhafarov.moneykeeper.filter.presentation.states

import com.dzhafarov.date_time.domain.Timestamp

data class FilterUiState(
    val endDateTime: com.dzhafarov.date_time.domain.Timestamp? = null,
    val startDateTime: com.dzhafarov.date_time.domain.Timestamp? = null,
    val reason: PaymentReasonUiState = PaymentReasonUiState(),
    val method: PaymentMethodUiState = PaymentMethodUiState(),
    val currency: CurrencyUiState = CurrencyUiState(),
    val description: DescriptionUiState = DescriptionUiState(),
    val priceRange: PriceRangeUiState = PriceRangeUiState(),
    val summary: SummaryUiState = SummaryUiState()
)
