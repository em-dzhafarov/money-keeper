package com.dzhafarov.expense.data.mapper

import com.dzhafarov.core.data.mapper.DTOMapper
import com.dzhafarov.date_time.domain.Timestamp
import com.dzhafarov.expense.data.db.ExpenseDTO
import com.dzhafarov.expense.domain.model.Expense
import javax.inject.Inject

internal class ExpenseMapper @Inject constructor(
    private val currencyMapper: CurrencyMapper,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val paymentReasonMapper: PaymentReasonMapper
) : DTOMapper<ExpenseDTO, Expense> {

    override suspend fun to(input: ExpenseDTO): Expense {
        return Expense(
            id = input.id,
            amount = input.amount,
            method = paymentMethodMapper.to(input.method),
            reason = paymentReasonMapper.to(input.reason),
            currency = currencyMapper.to(input.currency),
            description = input.description,
            time = Timestamp.of(input.time)
        )
    }

    override suspend fun from(input: Expense): ExpenseDTO {
        return ExpenseDTO(
            id = input.id,
            amount = input.amount,
            method = paymentMethodMapper.from(input.method),
            reason = paymentReasonMapper.from(input.reason),
            currency = currencyMapper.from(input.currency),
            description = input.description,
            time = input.time.milliseconds
        )
    }
}