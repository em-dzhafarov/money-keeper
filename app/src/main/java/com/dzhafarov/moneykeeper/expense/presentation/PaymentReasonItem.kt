package com.dzhafarov.moneykeeper.expense.presentation

import androidx.annotation.DrawableRes
import com.dzhafarov.moneykeeper.expense.domain.model.PaymentReason

data class PaymentReasonItem(
    val title: String,
    @DrawableRes val resourceId: Int,
    val value: PaymentReason
)
