package com.dzhafarov.moneykeeper.expense.presentation.mapper

import android.content.Context
import com.dzhafarov.moneykeeper.R
import com.dzhafarov.moneykeeper.core.domain.mapper.OneWayMapper
import com.dzhafarov.moneykeeper.expense.domain.model.PaymentReason
import com.dzhafarov.moneykeeper.expense.presentation.PaymentReasonItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PaymentReasonsMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : OneWayMapper<PaymentReason, PaymentReasonItem> {

    override suspend fun map(input: PaymentReason): PaymentReasonItem {
        val (titleRes, iconRes) = when (input) {
            is PaymentReason.Food -> {
                (R.string.payment_reason_food to R.drawable.payment_reason_food)
            }
            is PaymentReason.Study -> {
                (R.string.payment_reason_study to R.drawable.payment_reason_study)
            }
            is PaymentReason.Tax -> {
                (R.string.payment_reason_tax to R.drawable.payment_reason_tax)
            }
            is PaymentReason.Beauty -> {
                (R.string.payment_reason_beauty to R.drawable.payment_reason_beauty)
            }
            is PaymentReason.Accommodation -> {
                (R.string.payment_reason_accommodation to R.drawable.payment_reason_accommodation)
            }
            is PaymentReason.Entertainment -> {
                (R.string.payment_reason_entertainment to R.drawable.payment_reason_entertainment)
            }
            is PaymentReason.Subscription -> {
                (R.string.payment_reason_subscription to R.drawable.payment_reason_subscription)
            }
            is PaymentReason.Clothes -> {
                (R.string.payment_reason_clothes to R.drawable.payment_reason_clothes)
            }
            is PaymentReason.Transportation -> {
                (R.string.payment_reason_transportation to R.drawable.payment_reason_transportation)
            }
            is PaymentReason.Travel -> {
                (R.string.payment_reason_travel to R.drawable.payment_reason_travel)
            }
            is PaymentReason.Health -> {
                (R.string.payment_reason_health to R.drawable.payment_reason_health)
            }
        }

        return PaymentReasonItem(
            title = context.getString(titleRes),
            resourceId = iconRes,
            value = input
        )
    }
}