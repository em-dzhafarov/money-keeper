package com.dzhafarov.moneykeeper.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.core.presentation.ViewModelContract
import com.dzhafarov.moneykeeper.expense.domain.use_case.DeleteExpenseByIdUseCase
import com.dzhafarov.moneykeeper.expense.domain.use_case.ObserveExpensesUseCase
import com.dzhafarov.moneykeeper.expense.presentation.mapper.ExpenseMapper
import com.dzhafarov.moneykeeper.filter.domain.ObserveAppliedFiltersUseCase
import com.dzhafarov.moneykeeper.profile.use_case.GetCurrentUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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
    private val observeAppliedFiltersUseCase: ObserveAppliedFiltersUseCase,
    private val deleteExpenseByIdUseCase: DeleteExpenseByIdUseCase,
    private val expenseMapper: ExpenseMapper
) : ViewModel(), ViewModelContract<HomeUiState, HomeEvent, HomeUiAction> {

    private val _state = MutableStateFlow(HomeUiState())
    override val state: StateFlow<HomeUiState> = _state.asStateFlow()

    private val _events = Channel<HomeEvent>()
    override val events: Flow<HomeEvent> = _events.receiveAsFlow()

    init {
        loadWelcomeMessage()
        loadStrings()
        observeExpenses()
    }

    override fun reduce(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnAddExpenseClick -> onAddExpenseClick()
            is HomeUiAction.OnExpenseEditClick -> onExpenseEditClicked(action.id)
            is HomeUiAction.OnExpenseDeleteSwipe -> onExpenseDeleteSwiped(action.id)
            is HomeUiAction.OnViewLookingClick -> onViewLookingClick()
            is HomeUiAction.OnFilterClick -> onFilterClick()
            is HomeUiAction.OnSearchClick -> onSearchClick()
            is HomeUiAction.OnHomeClick -> onHomeClick()
        }
    }

    private fun onAddExpenseClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.AddExpense)
        }
    }

    private fun onExpenseEditClicked(id: Long) {
        viewModelScope.launch {
            _events.send(HomeEvent.EditExpense(id))
        }
    }

    private fun onViewLookingClick() {
        _state.update {
            it.copy(
                displayMode = when (it.displayMode) {
                    ExpensesDisplayMode.LIST -> ExpensesDisplayMode.GRID
                    ExpensesDisplayMode.GRID -> ExpensesDisplayMode.LIST
                }
            )
        }
    }

    private fun onFilterClick() {
        viewModelScope.launch {
            _events.send(
                HomeEvent.OpenFilter(
                    hasExpenses = _state.value.let {
                        it.expenses.isNotEmpty() || it.isFilterEmpty.not()
                    },
                    emptyExpenses = stringProvider.noExpensesToFilterOut()
                )
            )
        }
    }

    private fun onSearchClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenSearch)
        }
    }

    private fun onExpenseDeleteSwiped(id: Long) {
        val item = _state.value.expenses.find { it.id == id } ?: return
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

    private fun onHomeClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenAboutAppInfo)
        }
    }

    private fun loadWelcomeMessage() {
        viewModelScope.launch {
            val name = getCurrentUserProfileUseCase.execute().firstName

            _state.update {
                it.copy(
                    welcomeMessage = stringProvider.welcome(name)
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
                    addExpenseMessage = stringProvider.addExpense()
                )
            }
        }
    }

    private fun observeExpenses() {
        viewModelScope.launch {
            observeExpensesUseCase.execute()
                .combine(observeAppliedFiltersUseCase.execute(), ::Pair)
                .onEach { (_, predicates) ->
                    _state.update {
                        it.copy(
                            isFilterEmpty = predicates.isEmpty(),
                            emptyExpensesMessage = if (predicates.isNotEmpty()) {
                                stringProvider.noExpensesMatchTheGivenFilter()
                            } else {
                                stringProvider.noExpensesYet()
                            }
                        )
                    }
                }
                .map { (items, predicates) ->
                    items.filter { expense ->
                        predicates.all { it.invoke(expense) }
                    }
                }
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