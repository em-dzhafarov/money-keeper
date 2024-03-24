package com.dzhafarov.filters.presentation

import com.dzhafarov.expense.presentation.CurrencyItem
import com.dzhafarov.expense.presentation.PaymentMethodItem
import com.dzhafarov.expense.presentation.PaymentReasonItem

sealed class FilterUiAction {
    data object OnDismiss : FilterUiAction()
    data class OnPriceRangeChanged(val value: ClosedFloatingPointRange<Float>) : FilterUiAction()
    data object OnPriceRangeClicked : FilterUiAction()
    data object OnCurrencyClicked : FilterUiAction()
    data class OnCurrencySelected(val item: CurrencyItem) : FilterUiAction()
    data object OnPaymentMethodClicked : FilterUiAction()
    data class OnPaymentMethodSelected(val item: PaymentMethodItem) : FilterUiAction()
    data object OnPaymentReasonClicked : FilterUiAction()
    data class OnPaymentReasonSelected(val item: PaymentReasonItem) : FilterUiAction()
    data object OnDescriptionClicked : FilterUiAction()
    data class OnDescriptionValueChanged(val value: String) : FilterUiAction()
    data object OnApplyFiltersClicked : FilterUiAction()
    data object OnClearFiltersClicked : FilterUiAction()
    data object OnCancelClicked : FilterUiAction()
}