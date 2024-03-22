package com.dzhafarov.expense.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.expense.domain.model.PaymentMethod
import com.dzhafarov.expense.domain.repository.PaymentMethodRepository
import javax.inject.Inject

class GetPaymentMethodsUseCase @Inject constructor(
    private val paymentMethodRepository: PaymentMethodRepository
) : UseCaseSuspend<Nothing?, List<PaymentMethod>> {

    override suspend fun execute(input: Nothing?): List<PaymentMethod> {
        return paymentMethodRepository.getAll()
    }
}