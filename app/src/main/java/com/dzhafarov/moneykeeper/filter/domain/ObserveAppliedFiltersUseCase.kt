package com.dzhafarov.moneykeeper.filter.domain

import com.dzhafarov.core.domain.use_case.UseCase
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetCurrenciesUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetPaymentMethodsUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetPaymentReasonsUseCase
import com.dzhafarov.moneykeeper.expense.presentation.mapper.CurrencyMapper
import com.dzhafarov.moneykeeper.expense.presentation.mapper.PaymentMethodMapper
import com.dzhafarov.moneykeeper.expense.presentation.mapper.PaymentReasonMapper
import com.dzhafarov.moneykeeper.filter.domain.mapper.FilterDataMapper
import com.dzhafarov.moneykeeper.filter.presentation.FilterData
import com.dzhafarov.moneykeeper.filter.presentation.FiltersPredicate
import com.dzhafarov.moneykeeper.filter.storage.CurrentFiltersPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveAppliedFiltersUseCase @Inject constructor(
    private val preference: CurrentFiltersPreference,
    private val filterDataMapper: FilterDataMapper,
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val currencyMapper: CurrencyMapper,
    private val getPaymentMethodsUseCase: GetPaymentMethodsUseCase,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val getPaymentReasonsUseCase: GetPaymentReasonsUseCase,
    private val paymentReasonMapper: PaymentReasonMapper
) : UseCase<Nothing?, Flow<List<FiltersPredicate>>> {

    override fun execute(input: Nothing?): Flow<List<FiltersPredicate>> {
        return preference.observe()
            .map { filterDataMapper.from(it.orEmpty()) }
            .map { it ?: FilterData() }
            .map { data ->
                listOfNotNull(
                    amountPredicateOrNull(data),
                    currencyPredicateOrNull(data),
                    paymentMethodPredicateOrNull(data),
                    paymentReasonPredicateOrNull(data),
                    descriptionPredicateOrNull(data),
                    dateTimePredicateOrNull(data)
                )
            }
    }

    private fun amountPredicateOrNull(
        data: FilterData
    ): FiltersPredicate.Amount? {
        val (min, max) = data.let { it.minPrice to it.maxPrice }

        if (min != null && max != null) {
            return FiltersPredicate.Amount(min, max)
        }

        return null
    }

    private suspend fun currencyPredicateOrNull(
        data: FilterData
    ): FiltersPredicate.Currency? {
        if (data.currencyCodes.isNullOrEmpty()) {
            return null
        }

        val currencies = getCurrenciesUseCase.execute()
            .map { currencyMapper.map(it) }
            .filter { it.code in data.currencyCodes }
            .map { it.value }
            .toSet()

        if (currencies.isNotEmpty()) {
            return FiltersPredicate.Currency(currencies)
        }

        return null
    }

    private suspend fun paymentMethodPredicateOrNull(
        data: FilterData
    ): FiltersPredicate.PaymentMethod? {
        if (data.paymentMethods.isNullOrEmpty()) {
            return null
        }

        val methods = getPaymentMethodsUseCase.execute()
            .map { paymentMethodMapper.map(it) }
            .filter { it.title in data.paymentMethods }
            .map { it.value }
            .toSet()

        if (methods.isNotEmpty()) {
            return FiltersPredicate.PaymentMethod(methods)
        }

        return null
    }

    private suspend fun paymentReasonPredicateOrNull(
        data: FilterData
    ): FiltersPredicate.PaymentReason? {
        if (data.paymentReasons.isNullOrEmpty()) {
            return null
        }

        val reasons = getPaymentReasonsUseCase.execute()
            .map { paymentReasonMapper.map(it) }
            .filter { it.title in data.paymentReasons }
            .map { it.value }
            .toSet()

        if (reasons.isNotEmpty()) {
            return FiltersPredicate.PaymentReason(reasons)
        }

        return null
    }

    private fun descriptionPredicateOrNull(
        data: FilterData
    ): FiltersPredicate.Description? {
        val description = data.description

        if (description.isNullOrEmpty()) {
            return null
        }

        return FiltersPredicate.Description(description)
    }

    private fun dateTimePredicateOrNull(
        data: FilterData
    ): FiltersPredicate.DateTime? {
        val (start, end) = data.let { it.start to it.end }

        if (start != null && end != null) {
            return FiltersPredicate.DateTime(start, end)
        }

        return null
    }
}
