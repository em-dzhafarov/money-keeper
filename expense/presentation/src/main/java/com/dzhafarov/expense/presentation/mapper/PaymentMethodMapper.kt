package com.dzhafarov.expense.presentation.mapper

import com.dzhafarov.core.presentation.UIMapper
import com.dzhafarov.expense.domain.model.PaymentMethod
import com.dzhafarov.expense.presentation.DrawableResourceProvider
import com.dzhafarov.expense.presentation.ExpenseStringProvider
import com.dzhafarov.expense.presentation.PaymentMethodItem
import javax.inject.Inject

class PaymentMethodMapper @Inject constructor(
    private val stringProvider: ExpenseStringProvider,
    private val drawableResourceProvider: DrawableResourceProvider
) : UIMapper<PaymentMethod, PaymentMethodItem> {

    override suspend fun map(input: PaymentMethod): PaymentMethodItem {
        return when (input) {
            is PaymentMethod.CreditCard -> {
                PaymentMethodItem(
                    title = stringProvider.paymentMethodCreditCard,
                    resourceId = drawableResourceProvider.paymentMethodCreditCard,
                    value = input
                )
            }
            is PaymentMethod.Cash -> {
                PaymentMethodItem(
                    title = stringProvider.paymentMethodCash,
                    resourceId = drawableResourceProvider.paymentMethodCash,
                    value = input
                )
            }
            is PaymentMethod.Online -> {
                PaymentMethodItem(
                    title = stringProvider.paymentMethodOnline,
                    resourceId = drawableResourceProvider.paymentMethodOnline,
                    value = input
                )
            }
        }
    }
}