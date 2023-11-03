package com.dzhafarov.moneykeeper.expense.presentation

data class AddExpenseUiState(
    val title: String = "",
    val paymentReasonTitle: String = "",
    val paymentReasonError: String = "",
    val paymentReasons: List<PaymentReasonItem> = emptyList(),
    val selectedPaymentReason: PaymentReasonItem? = null,
    val paymentMethodTitle: String = "",
    val paymentMethodError: String = "",
    val paymentMethods: List<PaymentMethodItem> = emptyList(),
    val selectedPaymentMethod: PaymentMethodItem? = null,
    val amountValue: String = "",
    val amountError: String = "",
    val amountTitle: String = "",
    val amountLabel: String = "",
    val currencies: List<CurrencyItem> = emptyList(),
    val selectedCurrency: CurrencyItem? = null,
    val dateTimeTitle: String = "",
    val dateTimeSeparator: String = "-",
    val formattedDate: String = "",
    val formattedTime: String = "",
    val saveTitle: String = "",
    val descriptionTitle: String = "",
    val descriptionLabel: String = "",
    val descriptionValue: String = ""
)
