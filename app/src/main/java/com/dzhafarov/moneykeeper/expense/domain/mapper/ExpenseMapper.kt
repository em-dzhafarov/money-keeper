package com.dzhafarov.moneykeeper.expense.domain.mapper

import com.dzhafarov.moneykeeper.core.domain.mapper.Mapper
import com.dzhafarov.moneykeeper.expense.db.dto.ExpenseDTO
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class ExpenseMapper @Inject constructor(
    private val currencyMapper: CurrencyMapper,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val paymentReasonMapper: PaymentReasonMapper
) : Mapper<ExpenseDTO, Expense> {

    override suspend fun to(input: ExpenseDTO): Expense {
        return Expense(
            id = input.id,
            amount = input.amount,
            method = paymentMethodMapper.to(input.method),
            reason = paymentReasonMapper.to(input.reason),
            currency = currencyMapper.to(input.currency),
            title = input.title,
            description = input.description,
            time = LocalDateTime.ofInstant(Instant.ofEpochMilli(input.time), ZoneOffset.UTC)
        )
    }

    override suspend fun from(input: Expense): ExpenseDTO {
        return ExpenseDTO(
            id = input.id,
            amount = input.amount,
            method = paymentMethodMapper.from(input.method),
            reason = paymentReasonMapper.from(input.reason),
            currency = currencyMapper.from(input.currency),
            title = input.title,
            description = input.description,
            time = input.time.toInstant(ZoneOffset.UTC).toEpochMilli()
        )
    }
}