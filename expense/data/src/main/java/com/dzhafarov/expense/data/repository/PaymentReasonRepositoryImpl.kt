package com.dzhafarov.expense.data.repository

import com.dzhafarov.expense.domain.model.PaymentReason
import com.dzhafarov.expense.domain.repository.PaymentReasonRepository
import javax.inject.Inject

internal class PaymentReasonRepositoryImpl @Inject constructor() : PaymentReasonRepository {

    override suspend fun getAll(): List<PaymentReason> {
        return listOf(
            PaymentReason.Health,
            PaymentReason.Travel,
            PaymentReason.Transportation,
            PaymentReason.Clothes,
            PaymentReason.Subscription,
            PaymentReason.Entertainment,
            PaymentReason.Tax,
            PaymentReason.Beauty,
            PaymentReason.Study,
            PaymentReason.Food,
            PaymentReason.Accommodation
        )
    }
}