package com.dzhafarov.moneykeeper.expense.presentation.mapper

import com.dzhafarov.moneykeeper.core.domain.mapper.OneWayMapper
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.presentation.ExpenseItem
import com.dzhafarov.moneykeeper.home.presentation.DateTimePresentationMapper
import javax.inject.Inject

class ExpenseMapper @Inject constructor(
    private val paymentMethodMapper: PaymentMethodMapper,
    private val paymentReasonMapper: PaymentReasonMapper,
    private val currencyMapper: CurrencyMapper,
    private val dateTimePresentationMapper: DateTimePresentationMapper
) : OneWayMapper<Expense, ExpenseItem> {

    override suspend fun map(input: Expense): ExpenseItem {
        return ExpenseItem(
            id = input.id,
            amount = input.amount.toString(),
            method = paymentMethodMapper.map(input.method),
            reason = paymentReasonMapper.map(input.reason),
            currency = currencyMapper.map(input.currency),
            description = input.description,
            time = dateTimePresentationMapper.map(input.time)
        )
    }
}
