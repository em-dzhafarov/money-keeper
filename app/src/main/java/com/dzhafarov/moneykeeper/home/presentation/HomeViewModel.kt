package com.dzhafarov.moneykeeper.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.moneykeeper.core.domain.use_case.execute
import com.dzhafarov.moneykeeper.expense.domain.use_case.DeleteExpenseByIdUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.ObserveExpensesUseCase
import com.dzhafarov.moneykeeper.expense.presentation.ExpenseItem
import com.dzhafarov.moneykeeper.expense.presentation.mapper.ExpenseMapper
import com.dzhafarov.moneykeeper.profile.use_case.GetCurrentUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val stringProvider: HomeStringProvider,
    private val getCurrentUserProfileUseCase: GetCurrentUserProfileUseCase,
    private val observeExpensesUseCase: ObserveExpensesUseCase,
    private val deleteExpenseByIdUseCase: DeleteExpenseByIdUseCase,
    private val expenseMapper: ExpenseMapper
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiAction: MutableSharedFlow<HomeAction> = MutableSharedFlow(extraBufferCapacity = 1)
    val uiAction: SharedFlow<HomeAction> = _uiAction.asSharedFlow()

    init {
        loadWelcomeMessage()
        loadStrings()
        observeExpenses()
    }

    fun onAddExpenseClick() {
        viewModelScope.launch {
            _uiAction.emit(HomeAction.AddExpense)
        }
    }

    fun onNotificationsClick() {
        viewModelScope.launch {
            _uiAction.emit(HomeAction.OpenNotifications)
        }
    }

    fun onExpenseEditClicked(id: Long) {
        viewModelScope.launch {
            _uiAction.emit(HomeAction.EditExpense(id))
        }
    }

    fun onViewLookingClick() {

    }

    fun onFilterClick() {

    }

    fun onSearchClick() {

    }

    fun onExpenseDeleteSwiped(item: ExpenseItem) {
        val position = _uiState.value.expenses.indexOf(item)

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    expenses = state.expenses
                        .toMutableList()
                        .apply { remove(item) }
                        .toList()
                )
            }

            _uiAction.emit(
                HomeAction.DeleteExpense(
                    message = stringProvider.expenseDeletedMessage(),
                    actionLabel = stringProvider.undoDeleteButton(),
                    onActionPerformed = {
                        _uiState.update { state ->
                            state.copy(
                                expenses = state.expenses
                                    .toMutableList()
                                    .apply { add(position, item) }
                                    .toList()
                            )
                        }
                    },
                    onDismissed = {
                        deleteExpenseByIdUseCase.execute(item.id)
                    }
                )
            )
        }
    }

    fun onHomeClick() {
        viewModelScope.launch {
            _uiAction.emit(HomeAction.OpenAboutAppInfo)
        }
    }

    private fun loadWelcomeMessage() {
        viewModelScope.launch {
            val fullName = getCurrentUserProfileUseCase.execute()
                .let { "${it.firstName} ${it.lastName}" }

            _uiState.update {
                it.copy(
                    welcomeMessage = stringProvider.welcome(fullName)
                )
            }
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    title = stringProvider.title(),
                    emptyExpensesMessage = stringProvider.noExpensesYet(),
                    addExpenseMessage = stringProvider.addExpense(),
                    editExpenseLabel = stringProvider.editExpense(),
                    paidByPrefix = stringProvider.paidByPrefix()
                )
            }
        }
    }

    private fun observeExpenses() {
        viewModelScope.launch {
            observeExpensesUseCase.execute()
                .map { items ->
                    items.map { expenseMapper.map(it) }
                }
                .onEach { items ->
                    _uiState.update { state ->
                        state.copy(
                            expenses = items
                        )
                    }
                }
                .launchIn(this)
        }
    }
}