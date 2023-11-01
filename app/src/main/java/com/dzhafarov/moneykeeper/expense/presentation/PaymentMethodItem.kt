package com.dzhafarov.moneykeeper.expense.presentation

import androidx.annotation.DrawableRes
import com.dzhafarov.moneykeeper.expense.domain.model.PaymentMethod

data class PaymentMethodItem(
    val title: String,
    @DrawableRes val resourceId: Int,
    val value: PaymentMethod
)
