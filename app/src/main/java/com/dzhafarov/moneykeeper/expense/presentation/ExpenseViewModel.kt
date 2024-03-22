package com.dzhafarov.moneykeeper.expense.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.date_time.domain.Timestamp
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.expense.domain.use_case.DeleteExpenseByIdUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetCurrenciesUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetDefaultCurrencyUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetExpenseByIdUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetPaymentMethodsUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetPaymentReasonsUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.SaveExpenseUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.UpdateExpenseUseCase
import com.dzhafarov.moneykeeper.expense.presentation.mapper.CurrencyMapper
import com.dzhafarov.moneykeeper.expense.presentation.mapper.PaymentMethodMapper
import com.dzhafarov.moneykeeper.expense.presentation.mapper.PaymentReasonMapper
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
class ExpenseViewModel @Inject constructor(
    private val stringProvider: ExpenseStringProvider,
    private val getPaymentReasonsUseCase: GetPaymentReasonsUseCase,
    private val getPaymentMethodsUseCase: GetPaymentMethodsUseCase,
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    private val saveExpenseUseCase: SaveExpenseUseCase,
    private val updateExpenseUseCase: UpdateExpenseUseCase,
    private val deleteExpenseByIdUseCase: DeleteExpenseByIdUseCase,
    private val getExpenseByIdUseCase: GetExpenseByIdUseCase,
    private val paymentReasonMapper: PaymentReasonMapper,
    private val paymentMethodMapper: PaymentMethodMapper,
    private val currencyMapper: CurrencyMapper
) : ViewModel() {

    private val _state = MutableStateFlow(ExpenseUiState())
    val state: StateFlow<ExpenseUiState> = _state.asStateFlow()

    private val _events = Channel<ExpenseEvent>()
    val events: Flow<ExpenseEvent> = _events.receiveAsFlow()

    private var currentTimestamp: com.dzhafarov.date_time.domain.Timestamp = com.dzhafarov.date_time.domain.Timestamp.now()
    private var expenseId = 0L
    private val isEditMode: Boolean get() = expenseId != 0L

    val initialDate: Long get() = currentTimestamp.milliseconds
    val initialTime: Pair<Int, Int> get() = currentTimestamp.let { it.hours to it.minutes }


    fun initializeExpenseIfNeeded(id: Long) {
        expenseId = id

        if (isEditMode) {
            prepopulateWithExistingExpense(id)
        } else {
            onDateSelected(initialDate)
            onTimeSelected(initialTime)
        }

        loadStrings()
        loadPaymentReasons()
        loadPaymentMethods()
        loadCurrencies()
    }

    private fun prepopulateWithExistingExpense(id: Long) {
        viewModelScope.launch {
            val expense = getExpenseByIdUseCase.execute(id)

            if (expense != null) {
                _state.update {
                    it.copy(
                        selectedPaymentMethod = paymentMethodMapper.map(expense.method),
                        selectedPaymentReason = paymentReasonMapper.map(expense.reason),
                        selectedCurrency = currencyMapper.map(expense.currency),
                        amountValue = expense.amount.toString(),
                        descriptionValue = expense.description,
                        isDeleteVisible = true
                    )
                }

                val timestamp = expense.time
                onDateSelected(timestamp.milliseconds)
                onTimeSelected(timestamp.hours to timestamp.minutes)
            }
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _events.send(ExpenseEvent.NavigateBack)
        }
    }

    fun onPaymentReasonSelected(reason: PaymentReasonItem) {
        _state.update {
            it.copy(
                selectedPaymentReason = reason,
                paymentReasonError = ""
            )
        }
    }

    fun onPaymentMethodSelected(method: PaymentMethodItem) {
        _state.update {
            it.copy(
                selectedPaymentMethod = method,
                paymentMethodError = ""
            )
        }
    }

    fun onAmountChanged(value: String) {
        _state.update {
            it.copy(
                amountValue = value,
                amountError = ""
            )
        }
    }

    fun onDescriptionChanged(value: String) {
        _state.update {
            it.copy(
                descriptionValue = value
            )
        }
    }

    fun onCurrencySelected(value: CurrencyItem) {
        _state.update {
            it.copy(
                selectedCurrency = value
            )
        }
    }

    private suspend fun validateAndBuildExpense(): Expense? {
        _state.update {
            it.copy(
                amountError = "",
                paymentMethodError = "",
                paymentReasonError = ""
            )
        }

        val state = _state.value
        val amount = state.amountValue.toDoubleOrNull()
        val method = state.selectedPaymentMethod?.value
        val reason = state.selectedPaymentReason?.value
        val currency = state.selectedCurrency?.value

        if (amount == null) {
            _state.update {
                it.copy(
                    amountError = stringProvider.amountError()
                )
            }
        }

        if (method == null) {
            _state.update {
                it.copy(
                    paymentMethodError = stringProvider.paymentMethodError()
                )
            }
        }

        if (reason == null) {
            _state.update {
                it.copy(
                    paymentReasonError = stringProvider.paymentReasonError()
                )
            }
        }

        if (reason == null || method == null || amount == null || currency == null) {
            return null
        }

        return Expense(
            id = expenseId,
            amount = amount,
            method = method,
            reason = reason,
            currency = currency,
            description = state.descriptionValue,
            time = currentTimestamp
        )
    }

    fun onDeleteClick() {
        viewModelScope.launch {
            deleteExpenseByIdUseCase.execute(expenseId)
            _events.send(ExpenseEvent.ExpenseDeleted)
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            validateAndBuildExpense()?.let { expense ->
                if (isEditMode) {
                     updateExpenseUseCase.execute(expense)
                    _events.send(ExpenseEvent.ExpenseUpdated)
                } else {
                    saveExpenseUseCase.execute(expense)
                    _events.send(ExpenseEvent.ExpenseSaved)
                }
            }
        }
    }

    fun onSelectDate() {
        viewModelScope.launch {
            _events.send(
                ExpenseEvent.SelectDate(
                    millis = currentTimestamp.milliseconds
                )
            )
        }
    }

    fun onSelectTime() {
        viewModelScope.launch {
            _events.send(
                ExpenseEvent.SelectTime(
                    hour = currentTimestamp.hours,
                    minute = currentTimestamp.minutes
                )
            )
        }
    }

    fun onDateSelected(millis: Long) {
        currentTimestamp = com.dzhafarov.date_time.domain.Timestamp.of(
            dateMillis = millis,
            hours = currentTimestamp.hours,
            minutes = currentTimestamp.minutes
        )

        _state.update {
            it.copy(
                formattedDate = currentTimestamp.formattedDate
            )
        }
    }

    fun onTimeSelected(data: Pair<Int, Int>) {
        val (hour, minute) = data

        currentTimestamp = com.dzhafarov.date_time.domain.Timestamp.of(
            dateMillis = currentTimestamp.milliseconds,
            hours = hour,
            minutes = minute,
        )

        _state.update {
            it.copy(
                formattedTime = currentTimestamp.formattedTime
            )
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    title = stringProvider.title(isEditMode),
                    paymentReasonTitle = stringProvider.paymentReasonTitle(),
                    paymentMethodTitle = stringProvider.paymentMethodTitle(),
                    dateTimeTitle = stringProvider.dateTimeTitle(),
                    amountTitle = stringProvider.amountTitle(),
                    amountLabel = stringProvider.amountLabel(),
                    descriptionLabel = stringProvider.descriptionLabel(),
                    descriptionTitle = stringProvider.descriptionTitle(),
                    saveTitle = stringProvider.saveTitle(isEditMode),
                    deleteTitle = stringProvider.deleteTitle()
                )
            }
        }
    }

    private fun loadPaymentReasons() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    paymentReasons = getPaymentReasonsUseCase.execute()
                        .map { paymentReasonMapper.map(it) }
                )
            }
        }
    }

    private fun loadPaymentMethods() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    paymentMethods = getPaymentMethodsUseCase.execute()
                        .map { paymentMethodMapper.map(it) }
                )
            }
        }
    }

    private fun loadCurrencies() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    selectedCurrency = getDefaultCurrencyUseCase.execute()
                        .let { currencyMapper.map(it) },
                    currencies = getCurrenciesUseCase.execute()
                        .map { currencyMapper.map(it) }
                )
            }
        }
    }
}