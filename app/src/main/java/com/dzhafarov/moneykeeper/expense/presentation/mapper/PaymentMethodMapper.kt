package com.dzhafarov.moneykeeper.expense.presentation.mapper

import android.content.Context
import com.dzhafarov.moneykeeper.R
import com.dzhafarov.core.domain.mapper.OneWayMapper
import com.dzhafarov.moneykeeper.expense.domain.model.PaymentMethod
import com.dzhafarov.moneykeeper.expense.presentation.PaymentMethodItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PaymentMethodMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : OneWayMapper<PaymentMethod, PaymentMethodItem> {

    override suspend fun map(input: PaymentMethod): PaymentMethodItem {
        val (titleRes, iconRes) = when (input) {
            is PaymentMethod.CreditCard -> {
                (R.string.payment_method_credit_card to R.drawable.payment_method_credit_card)
            }
            is PaymentMethod.Cash -> {
                (R.string.payment_method_cash to R.drawable.payment_method_cash)
            }
            is PaymentMethod.Online -> {
                (R.string.payment_method_online to R.drawable.payment_method_online)
            }
        }

        return PaymentMethodItem(
            title = context.getString(titleRes),
            resourceId = iconRes,
            value = input
        )
    }
}