package com.dzhafarov.expense.presentation.mapper

import com.dzhafarov.core.domain.mapper.OneWayMapper
import com.dzhafarov.expense.domain.model.PaymentReason
import com.dzhafarov.expense.presentation.DrawableResourceProvider
import com.dzhafarov.expense.presentation.ExpenseStringProvider
import com.dzhafarov.expense.presentation.PaymentReasonItem
import javax.inject.Inject

class PaymentReasonMapper @Inject constructor(
    private val stringProvider: ExpenseStringProvider,
    private val drawableResourceProvider: DrawableResourceProvider
) : OneWayMapper<PaymentReason, PaymentReasonItem> {

    override suspend fun map(input: PaymentReason): PaymentReasonItem {
        return when (input) {
            is PaymentReason.Food -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonFood,
                    resourceId = drawableResourceProvider.paymentReasonFood,
                    value = input
                )
            }
            is PaymentReason.Study -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonStudy,
                    resourceId = drawableResourceProvider.paymentReasonStudy,
                    value = input
                )
            }
            is PaymentReason.Tax -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonTax,
                    resourceId = drawableResourceProvider.paymentReasonTax,
                    value = input
                )
            }
            is PaymentReason.Beauty -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonBeauty,
                    resourceId = drawableResourceProvider.paymentReasonBeauty,
                    value = input
                )
            }
            is PaymentReason.Accommodation -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonAccommodation,
                    resourceId = drawableResourceProvider.paymentReasonAccommodation,
                    value = input
                )
            }
            is PaymentReason.Entertainment -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonEntertainment,
                    resourceId = drawableResourceProvider.paymentReasonEntertainment,
                    value = input
                )
            }
            is PaymentReason.Subscription -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonSubscription,
                    resourceId = drawableResourceProvider.paymentReasonSubscription,
                    value = input
                )
            }
            is PaymentReason.Clothes -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonClothes,
                    resourceId = drawableResourceProvider.paymentReasonClothes,
                    value = input
                )
            }
            is PaymentReason.Transportation -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonTransportation,
                    resourceId = drawableResourceProvider.paymentReasonTransportation,
                    value = input
                )
            }
            is PaymentReason.Travel -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonTravel,
                    resourceId = drawableResourceProvider.paymentReasonTravel,
                    value = input
                )
            }
            is PaymentReason.Health -> {
                PaymentReasonItem(
                    title = stringProvider.paymentReasonHealth,
                    resourceId = drawableResourceProvider.paymentReasonHealth,
                    value = input
                )
            }
        }
    }
}