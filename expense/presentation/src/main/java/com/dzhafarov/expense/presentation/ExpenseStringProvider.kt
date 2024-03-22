package com.dzhafarov.expense.presentation

interface ExpenseStringProvider {
    fun title(isEdit: Boolean): String
    val paymentReasonTitle: String
    val paymentReasonError: String
    val paymentMethodTitle: String
    val paymentMethodError: String
    val amountTitle: String
    fun saveTitle(isEdit: Boolean): String
    val deleteTitle: String
    val amountLabel: String
    val amountError: String
    val dateTimeTitle: String
    val descriptionTitle: String
    val descriptionLabel: String
    fun plnCurrencyName(isFull: Boolean): String
    fun usdCurrencyName(isFull: Boolean): String
    fun euroCurrencyName(isFull: Boolean): String
    fun todayAtTime(time: String): String
    fun yesterdayAtTime(time: String): String
    fun atDateAndAtTime(date: String, time: String): String
    val paymentMethodCreditCard: String
    val paymentMethodCash: String
    val paymentMethodOnline: String
    val paymentReasonFood: String
    val paymentReasonStudy: String
    val paymentReasonTax: String
    val paymentReasonBeauty: String
    val paymentReasonAccommodation: String
    val paymentReasonEntertainment: String
    val paymentReasonSubscription: String
    val paymentReasonClothes: String
    val paymentReasonTransportation: String
    val paymentReasonTravel: String
    val paymentReasonHealth: String
}