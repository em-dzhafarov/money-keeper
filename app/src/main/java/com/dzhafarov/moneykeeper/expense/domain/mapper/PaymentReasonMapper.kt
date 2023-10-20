package com.dzhafarov.moneykeeper.expense.domain.mapper

import com.dzhafarov.moneykeeper.core.domain.mapper.Mapper
import com.dzhafarov.moneykeeper.expense.db.dto.PaymentReasonDTO
import com.dzhafarov.moneykeeper.expense.domain.model.PaymentReason
import javax.inject.Inject

class PaymentReasonMapper @Inject constructor() : Mapper<PaymentReasonDTO, PaymentReason> {

    override suspend fun to(input: PaymentReasonDTO): PaymentReason {
        return when (input) {
            PaymentReasonDTO.FOOD -> PaymentReason.Food
            PaymentReasonDTO.STUDY -> PaymentReason.Study
            PaymentReasonDTO.ACCOMMODATION -> PaymentReason.Accommodation
            PaymentReasonDTO.BEAUTY -> PaymentReason.Beauty
            PaymentReasonDTO.TAX -> PaymentReason.Tax
            PaymentReasonDTO.CLOTHES -> PaymentReason.Clothes
            PaymentReasonDTO.ENTERTAINMENT -> PaymentReason.Entertainment
            PaymentReasonDTO.HEALTH -> PaymentReason.Health
            PaymentReasonDTO.SUBSCRIPTIONS -> PaymentReason.Subscription
            PaymentReasonDTO.TRANSPORTATION -> PaymentReason.Transportation
            PaymentReasonDTO.TRAVEL -> PaymentReason.Travel
        }
    }

    override suspend fun from(input: PaymentReason): PaymentReasonDTO {
        return when (input) {
            is PaymentReason.Food -> PaymentReasonDTO.FOOD
            is PaymentReason.Study -> PaymentReasonDTO.STUDY
            is PaymentReason.Accommodation -> PaymentReasonDTO.ACCOMMODATION
            is PaymentReason.Beauty -> PaymentReasonDTO.BEAUTY
            is PaymentReason.Tax -> PaymentReasonDTO.TAX
            is PaymentReason.Clothes -> PaymentReasonDTO.CLOTHES
            is PaymentReason.Entertainment -> PaymentReasonDTO.ENTERTAINMENT
            is PaymentReason.Health -> PaymentReasonDTO.HEALTH
            is PaymentReason.Subscription -> PaymentReasonDTO.SUBSCRIPTIONS
            is PaymentReason.Transportation -> PaymentReasonDTO.TRANSPORTATION
            is PaymentReason.Travel -> PaymentReasonDTO.TRAVEL
        }
    }
}