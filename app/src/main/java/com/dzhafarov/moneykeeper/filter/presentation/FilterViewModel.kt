package com.dzhafarov.moneykeeper.filter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetCurrenciesUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetPaymentMethodsUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetPaymentReasonsUseCase
import com.dzhafarov.moneykeeper.expense.presentation.CurrencyItem
import com.dzhafarov.moneykeeper.expense.presentation.PaymentMethodItem
import com.dzhafarov.moneykeeper.expense.presentation.PaymentReasonItem
import com.dzhafarov.moneykeeper.expense.presentation.mapper.CurrencyMapper
import com.dzhafarov.moneykeeper.expense.presentation.mapper.PaymentMethodMapper
import com.dzhafarov.moneykeeper.expense.presentation.mapper.PaymentReasonMapper
import com.dzhafarov.moneykeeper.filter.domain.GetCurrentFiltersUseCase
import com.dzhafarov.moneykeeper.filter.domain.GetMinAndMaxAmountUseCase
import com.dzhafarov.moneykeeper.filter.domain.SaveCurrentFiltersUseCase
import com.dzhafarov.moneykeeper.filter.presentation.states.FilterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getMinAndMaxAmountUseCase: GetMinAndMaxAmountUseCase,
    private val saveCurrentFiltersUseCase: SaveCurrentFiltersUseCase,
    private val getCurrentFiltersUseCase: GetCurrentFiltersUseCase,
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val currencyMapper: CurrencyMapper,
    private val getPaymentMethodsUseCase: GetPaymentMethodsUseCase,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val getPaymentReasonsUseCase: GetPaymentReasonsUseCase,
    private val paymentReasonMapper: PaymentReasonMapper,
    private val stringProvider: FilterStringProvider
) : ViewModel() {

    private val _state = MutableStateFlow(FilterUiState())
    val state: StateFlow<FilterUiState> = _state.asStateFlow()

    private val _events = Channel<FilterEvent>()
    val events: Flow<FilterEvent> = _events.receiveAsFlow()

    private var defaultFilterData = buildFilterData()

    init {
        initialize()
    }

    fun onDismiss() {
        viewModelScope.launch {
            _events.send(FilterEvent.Dismiss)
        }
    }

    fun onPriceRangeChanged(value: ClosedFloatingPointRange<Float>) {
        _state.update {
            it.copy(
                priceRange = it.priceRange.copy(
                    minAmount = value.start.toDouble(),
                    maxAmount = value.endInclusive.toDouble()
                )
            )
        }

        invalidateSummary()
    }

    fun onPriceRangeClicked() {
        _state.update {
            it.copy(
                priceRange = it.priceRange.copy(
                    isExpanded = it.priceRange.isExpanded.not()
                )
            )
        }
    }

    fun onCurrencyClicked() {
        _state.update {
            it.copy(
                currency = it.currency.copy(
                    isExpanded = it.currency.isExpanded.not()
                )
            )
        }
    }

    fun onCurrencySelected(item: CurrencyItem) {
        _state.update {
            it.copy(
                currency = it.currency.copy(
                    selected = it.currency.selected.toMutableList().apply {
                        if (item in this) {
                            remove(item)
                        } else {
                            add(item)
                        }
                    }.toList()
                )
            )
        }

        invalidateSummary()
    }

    fun onPaymentMethodClicked() {
        _state.update {
            it.copy(
                method = it.method.copy(
                    isExpanded = it.method.isExpanded.not()
                )
            )
        }
    }

    fun onPaymentMethodSelected(item: PaymentMethodItem) {
        _state.update {
            it.copy(
                method = it.method.copy(
                    selected = it.method.selected.toMutableList().apply {
                        if (item in this) {
                            remove(item)
                        } else {
                            add(item)
                        }
                    }.toList()
                )
            )
        }

        invalidateSummary()
    }

    fun onPaymentReasonClicked() {
        _state.update {
            it.copy(
                reason = it.reason.copy(
                    isExpanded = it.reason.isExpanded.not()
                )
            )
        }
    }

    fun onPaymentReasonSelected(item: PaymentReasonItem) {
        _state.update {
            it.copy(
                reason = it.reason.copy(
                    selected = it.reason.selected.toMutableList().apply {
                        if (item in this) {
                            remove(item)
                        } else {
                            add(item)
                        }
                    }.toList()
                )
            )
        }

        invalidateSummary()
    }

    fun onDescriptionClicked() {
        _state.update {
            it.copy(
                description = it.description.copy(
                    isExpanded = it.description.isExpanded.not()
                )
            )
        }
    }

    fun onDescriptionValueChanged(value: String) {
        _state.update {
            it.copy(
                description = it.description.copy(
                    value = value
                )
            )
        }

        invalidateSummary()
    }

    fun onApplyFiltersClicked() {
        viewModelScope.launch {
            val filters = buildFilterData()

            if (filters != defaultFilterData) {
                saveCurrentFiltersUseCase.execute(filters)
            } else {
                saveCurrentFiltersUseCase.execute(FilterData())
            }

            _events.send(FilterEvent.Dismiss)
        }
    }

    fun onClearFiltersClicked() {
        viewModelScope.launch {
            initialize(FilterData())
        }
    }

    fun onCancelClicked() {
        viewModelScope.launch {
            _events.send(FilterEvent.Dismiss)
        }
    }

    private fun buildFilterData(): FilterData {
        return _state.value.let {
            FilterData(
                minPrice = it.priceRange.minAmount ?: it.priceRange.initialMinAmount,
                maxPrice = it.priceRange.maxAmount ?: it.priceRange.initialMaxAmount,
                currencyCodes = it.currency.selected.map { it.code }.toSet(),
                paymentMethods = it.method.selected.map { it.title }.toSet(),
                paymentReasons = it.reason.selected.map { it.title }.toSet(),
                description = it.description.value,
                start = it.startDateTime?.milliseconds,
                end = it.endDateTime?.milliseconds
            )
        }
    }

    private fun initialize(seed: FilterData? = null) {
        viewModelScope.launch {
            val current = seed ?: getCurrentFiltersUseCase.execute()
            val (min, max) = getMinAndMaxAmountUseCase.execute()
            val currencies = getCurrenciesUseCase.execute().map { currencyMapper.map(it) }
            val currencyCodes = current?.currencyCodes.orEmpty()
            val methods = getPaymentMethodsUseCase.execute().map { paymentMethodMapper.map(it) }
            val methodTitles = current?.paymentMethods.orEmpty()
            val reasons = getPaymentReasonsUseCase.execute().map { paymentReasonMapper.map(it) }
            val reasonTitles = current?.paymentReasons.orEmpty()

            defaultFilterData = defaultFilterData.copy(minPrice = min, maxPrice = max)

            _state.update {
                it.copy(
                    description = it.description.copy(
                        title = stringProvider.descriptionTitle,
                        hint = stringProvider.descriptionHint,
                        value = current?.description.orEmpty(),
                        isExpanded = current?.description.isNullOrEmpty().not()
                    ),
                    priceRange = it.priceRange.copy(
                        title = stringProvider.priceRangeTitle,
                        initialMinAmount = min,
                        initialMaxAmount = max,
                        minAmount = current?.minPrice,
                        maxAmount = current?.maxPrice,
                        isExpanded = (current?.minPrice != null && current.minPrice != defaultFilterData.minPrice ||
                                current?.maxPrice != null && current.maxPrice != defaultFilterData.maxPrice)
                    ),
                    currency = it.currency.copy(
                        currencies = currencies,
                        selected = currencies.filter { item -> item.code in currencyCodes },
                        isExpanded = current?.currencyCodes != null && current.currencyCodes != defaultFilterData.currencyCodes,
                        title = stringProvider.currencyTitle
                    ),
                    method = it.method.copy(
                        methods = methods,
                        selected = methods.filter { item -> item.title in methodTitles },
                        isExpanded = current?.paymentMethods != null && current.paymentMethods != defaultFilterData.paymentMethods,
                        title = stringProvider.paymentMethodTitle,
                    ),
                    reason = it.reason.copy(
                        reasons = reasons,
                        selected = reasons.filter { item -> item.title in reasonTitles },
                        isExpanded = current?.paymentReasons != null && current.paymentReasons != defaultFilterData.paymentReasons,
                        title = stringProvider.paymentReasonTitle
                    ),
                    summary = it.summary.copy(
                        apply = stringProvider.applyFilters,
                        clear = stringProvider.clearFilters,
                        cancel = stringProvider.cancel
                    )
                )
            }

            invalidateSummary()
        }
    }

    private fun invalidateSummary() {
        _state.update {
            it.copy(
                summary = it.summary.copy(
                    isApplied = buildFilterData() != defaultFilterData
                )
            )
        }
    }
}
