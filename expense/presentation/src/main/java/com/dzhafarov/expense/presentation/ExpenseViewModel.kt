package com.dzhafarov.expense.presentation

import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.core.presentation.ViewModelContract
import com.dzhafarov.date_time.domain.Timestamp
import com.dzhafarov.expense.domain.model.Expense
import com.dzhafarov.expense.domain.use_case.DeleteExpenseByIdUseCase
import com.dzhafarov.expense.domain.use_case.GetCurrenciesUseCase
import com.dzhafarov.expense.domain.use_case.GetDefaultCurrencyUseCase
import com.dzhafarov.expense.domain.use_case.GetExpenseByIdUseCase
import com.dzhafarov.expense.domain.use_case.GetPaymentMethodsUseCase
import com.dzhafarov.expense.domain.use_case.GetPaymentReasonsUseCase
import com.dzhafarov.expense.domain.use_case.SaveExpenseUseCase
import com.dzhafarov.expense.domain.use_case.UpdateExpenseUseCase
import com.dzhafarov.expense.presentation.mapper.CurrencyMapper
import com.dzhafarov.expense.presentation.mapper.PaymentMethodMapper
import com.dzhafarov.expense.presentation.mapper.PaymentReasonMapper
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
) : ViewModelContract<ExpenseUiState, ExpenseEvent, ExpenseUiAction>() {

    private val _state = MutableStateFlow(ExpenseUiState())
    override val state: StateFlow<ExpenseUiState> = _state.asStateFlow()

    private val _events = Channel<ExpenseEvent>()
    override val events: Flow<ExpenseEvent> = _events.receiveAsFlow()

    private var currentTimestamp: Timestamp = Timestamp.now()
    private var expenseId = 0L
    private val isEditMode: Boolean get() = expenseId != 0L

    val initialDate: Long get() = currentTimestamp.milliseconds
    val initialTime: Pair<Int, Int> get() = currentTimestamp.let { it.hours to it.minutes }

    override fun reduce(action: ExpenseUiAction) {
        when (action) {
            is ExpenseUiAction.OnAmountChanged -> onAmountChanged(action.value)
            is ExpenseUiAction.OnBackPressed -> onBackPressed()
            is ExpenseUiAction.OnCurrencySelected -> onCurrencySelected(action.value)
            is ExpenseUiAction.OnDateSelected -> onDateSelected(action.millis)
            is ExpenseUiAction.OnDeleteClick -> onDeleteClick()
            is ExpenseUiAction.OnDescriptionChanged -> onDescriptionChanged(action.value)
            is ExpenseUiAction.OnPaymentMethodSelected -> onPaymentMethodSelected(action.method)
            is ExpenseUiAction.OnPaymentReasonSelected -> onPaymentReasonSelected(action.reason)
            is ExpenseUiAction.OnSaveClick -> onSaveClick()
            is ExpenseUiAction.OnSelectDate -> onSelectDate()
            is ExpenseUiAction.OnSelectTime -> onSelectTime()
            is ExpenseUiAction.OnTimeSelected -> onTimeSelected(action.data)
        }
    }

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
        launch {
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

    private fun onBackPressed() {
        launch {
            _events.send(ExpenseEvent.NavigateBack)
        }
    }

    private fun onPaymentReasonSelected(reason: PaymentReasonItem) {
        _state.update {
            it.copy(
                selectedPaymentReason = reason,
                paymentReasonError = ""
            )
        }
    }

    private fun onPaymentMethodSelected(method: PaymentMethodItem) {
        _state.update {
            it.copy(
                selectedPaymentMethod = method,
                paymentMethodError = ""
            )
        }
    }

    private fun onAmountChanged(value: String) {
        _state.update {
            it.copy(
                amountValue = value,
                amountError = ""
            )
        }
    }

    private fun onDescriptionChanged(value: String) {
        _state.update {
            it.copy(
                descriptionValue = value
            )
        }
    }

    private fun onCurrencySelected(value: CurrencyItem) {
        _state.update {
            it.copy(
                selectedCurrency = value
            )
        }
    }

    private fun validateAndBuildExpense(): Expense? {
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
                    amountError = stringProvider.amountError
                )
            }
        }

        if (method == null) {
            _state.update {
                it.copy(
                    paymentMethodError = stringProvider.paymentMethodError
                )
            }
        }

        if (reason == null) {
            _state.update {
                it.copy(
                    paymentReasonError = stringProvider.paymentReasonError
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

    private fun onDeleteClick() {
        launch {
            deleteExpenseByIdUseCase.execute(expenseId)
            _events.send(ExpenseEvent.ExpenseDeleted)
        }
    }

    private fun onSaveClick() {
        launch {
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

    private fun onSelectDate() {
        launch {
            _events.send(
                ExpenseEvent.SelectDate(
                    millis = currentTimestamp.milliseconds
                )
            )
        }
    }

    private fun onSelectTime() {
        launch {
            _events.send(
                ExpenseEvent.SelectTime(
                    hour = currentTimestamp.hours,
                    minute = currentTimestamp.minutes
                )
            )
        }
    }

    private fun onDateSelected(millis: Long) {
        currentTimestamp = Timestamp.of(
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

    private fun onTimeSelected(data: Pair<Int, Int>) {
        val (hour, minute) = data

        currentTimestamp = Timestamp.of(
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
        launch {
            _state.update {
                it.copy(
                    title = stringProvider.title(isEditMode),
                    paymentReasonTitle = stringProvider.paymentReasonTitle,
                    paymentMethodTitle = stringProvider.paymentMethodTitle,
                    dateTimeTitle = stringProvider.dateTimeTitle,
                    amountTitle = stringProvider.amountTitle,
                    amountLabel = stringProvider.amountLabel,
                    descriptionLabel = stringProvider.descriptionLabel,
                    descriptionTitle = stringProvider.descriptionTitle,
                    saveTitle = stringProvider.saveTitle(isEditMode),
                    deleteTitle = stringProvider.deleteTitle
                )
            }
        }
    }

    private fun loadPaymentReasons() {
        launch {
            _state.update { state ->
                state.copy(
                    paymentReasons = getPaymentReasonsUseCase.execute()
                        .map { paymentReasonMapper.map(it) }
                )
            }
        }
    }

    private fun loadPaymentMethods() {
        launch {
            _state.update { state ->
                state.copy(
                    paymentMethods = getPaymentMethodsUseCase.execute()
                        .map { paymentMethodMapper.map(it) }
                )
            }
        }
    }

    private fun loadCurrencies() {
        launch {
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