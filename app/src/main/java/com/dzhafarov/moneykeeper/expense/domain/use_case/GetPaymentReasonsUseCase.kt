package com.dzhafarov.moneykeeper.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.expense.domain.model.PaymentReason
import com.dzhafarov.moneykeeper.expense.domain.repository.PaymentReasonRepository
import javax.inject.Inject

class GetPaymentReasonsUseCase @Inject constructor(
    private val paymentReasonRepository: PaymentReasonRepository
) : UseCaseSuspend<Nothing?, List<PaymentReason>> {

    override suspend fun execute(input: Nothing?): List<PaymentReason> {
        return paymentReasonRepository.getAll()
    }
}
