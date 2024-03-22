package com.dzhafarov.expense.presentation

interface DrawableResourceProvider {
    val currencyPln: Int
    val currencyUsd: Int
    val currencyEur: Int
    val paymentMethodCreditCard: Int
    val paymentMethodCash: Int
    val paymentMethodOnline: Int
    val paymentReasonFood: Int
    val paymentReasonStudy: Int
    val paymentReasonTax: Int
    val paymentReasonBeauty: Int
    val paymentReasonAccommodation: Int
    val paymentReasonEntertainment: Int
    val paymentReasonSubscription: Int
    val paymentReasonClothes: Int
    val paymentReasonTransportation: Int
    val paymentReasonTravel: Int
    val paymentReasonHealth: Int
}