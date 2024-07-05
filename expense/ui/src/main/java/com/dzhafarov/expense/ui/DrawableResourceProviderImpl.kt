package com.dzhafarov.expense.ui

import com.dzhafarov.expense.presentation.DrawableResourceProvider
import javax.inject.Inject

internal class DrawableResourceProviderImpl @Inject constructor() : DrawableResourceProvider {
    override val currencyPln: Int = R.drawable.currency_zloty
    override val currencyUsd: Int = R.drawable.currency_dollar
    override val currencyEur: Int = R.drawable.currency_euro
    override val paymentMethodCreditCard: Int = R.drawable.payment_method_credit_card
    override val paymentMethodCash: Int = R.drawable.payment_method_cash
    override val paymentMethodOnline: Int = R.drawable.payment_method_online
    override val paymentReasonFood: Int = R.drawable.payment_reason_food
    override val paymentReasonStudy: Int = R.drawable.payment_reason_study
    override val paymentReasonTax: Int = R.drawable.payment_reason_tax
    override val paymentReasonBeauty: Int = R.drawable.payment_reason_beauty
    override val paymentReasonAccommodation: Int = R.drawable.payment_reason_accommodation
    override val paymentReasonEntertainment: Int = R.drawable.payment_reason_entertainment
    override val paymentReasonSubscription: Int = R.drawable.payment_reason_subscription
    override val paymentReasonClothes: Int = R.drawable.payment_reason_clothes
    override val paymentReasonTransportation: Int = R.drawable.payment_reason_transportation
    override val paymentReasonTravel: Int = R.drawable.payment_reason_travel
    override val paymentReasonHealth: Int = R.drawable.payment_reason_health
}
