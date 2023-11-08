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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    private val _events = Channel<HomeEvent>()
    val events: Flow<HomeEvent> = _events.receiveAsFlow()

    init {
        loadWelcomeMessage()
        loadStrings()
        observeExpenses()
    }

    fun onAddExpenseClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.AddExpense)
        }
    }

    fun onNotificationsClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenNotifications)
        }
    }

    fun onExpenseEditClicked(id: Long) {
        viewModelScope.launch {
            _events.send(HomeEvent.EditExpense(id))
        }
    }

    fun onViewLookingClick() {
        _state.update {
            it.copy(
                lookingView = when (it.lookingView) {
                    HomeLookView.LIST -> HomeLookView.GRID
                    HomeLookView.GRID -> HomeLookView.LIST
                }
            )
        }
    }

    fun onFilterClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenFilter)
        }
    }

    fun onSearchClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenSearch)
        }
    }

    fun onExpenseDeleteSwiped(item: ExpenseItem) {
        val position = _state.value.expenses.indexOf(item)

        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    expenses = state.expenses
                        .toMutableList()
                        .apply { remove(item) }
                        .toList()
                )
            }

            _events.send(
                HomeEvent.DeleteExpense(
                    message = stringProvider.expenseDeletedMessage(),
                    actionLabel = stringProvider.undoDeleteButton(),
                    onActionPerformed = {
                        _state.update { state ->
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
            _events.send(HomeEvent.OpenAboutAppInfo)
        }
    }

    private fun loadWelcomeMessage() {
        viewModelScope.launch {
            val fullName = getCurrentUserProfileUseCase.execute()
                .let { "${it.firstName} ${it.lastName}" }

            _state.update {
                it.copy(
                    welcomeMessage = stringProvider.welcome(fullName)
                )
            }
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _state.update {
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
                    _state.update { state ->
                        state.copy(
                            expenses = items
                        )
                    }
                }
                .launchIn(this)
        }
    }
}