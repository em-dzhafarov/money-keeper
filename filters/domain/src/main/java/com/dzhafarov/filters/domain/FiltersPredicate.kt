package com.dzhafarov.filters.domain

import com.dzhafarov.expense.domain.model.Expense
import com.dzhafarov.expense.domain.model.Currency as CurrencyModel
import com.dzhafarov.expense.domain.model.PaymentReason as PaymentReasonModel
import com.dzhafarov.expense.domain.model.PaymentMethod as PaymentMethodModel

sealed class FiltersPredicate : (Expense) -> Boolean {

    data class Amount(
        val min: Double,
        val max: Double
    ) : FiltersPredicate() {
        override fun invoke(expense: Expense): Boolean {
            return expense.amount in (min..max)
        }
    }

    data class Currency(
        private val currencies: Set<CurrencyModel>
    ) : FiltersPredicate() {
        override fun invoke(expense: Expense): Boolean {
            return expense.currency in currencies
        }
    }

    data class PaymentReason(
        private val reasons: Set<PaymentReasonModel>
    ) : FiltersPredicate() {
        override fun invoke(expense: Expense): Boolean {
            return expense.reason in reasons
        }
    }

    data class PaymentMethod(
        private val methods: Set<PaymentMethodModel>
    ) : FiltersPredicate() {
        override fun invoke(expense: Expense): Boolean {
            return expense.method in methods
        }
    }

    data class Description(
        private val description: String
    ) : FiltersPredicate() {
        override fun invoke(expense: Expense): Boolean {
            return expense.description.contains(description, true)
        }
    }

    data class DateTime(
        private val start: Long,
        private val end: Long
    ) : FiltersPredicate() {
        override fun invoke(expense: Expense): Boolean {
            return expense.time.inBetween(start, end)
        }
    }
}
