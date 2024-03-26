package com.dzhafarov.expense.presentation

sealed class ExpenseUiAction {
    data object OnBackPressed : ExpenseUiAction()
    data class OnPaymentReasonSelected(val reason: PaymentReasonItem) : ExpenseUiAction()
    data class OnPaymentMethodSelected(val method: PaymentMethodItem) : ExpenseUiAction()
    data class OnCurrencySelected(val value: CurrencyItem) : ExpenseUiAction()
    data class OnAmountChanged(val value: String) : ExpenseUiAction()
    data class OnDescriptionChanged(val value: String) : ExpenseUiAction()
    data object OnDeleteClick : ExpenseUiAction()
    data object OnSaveClick : ExpenseUiAction()
    data object OnSelectDate : ExpenseUiAction()
    data object OnSelectTime : ExpenseUiAction()
    data class OnDateSelected(val millis: Long) : ExpenseUiAction()
    data class OnTimeSelected(val data: Pair<Int, Int>) : ExpenseUiAction()
}