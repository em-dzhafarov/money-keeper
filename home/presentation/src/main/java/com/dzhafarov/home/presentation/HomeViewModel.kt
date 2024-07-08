package com.dzhafarov.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.core.presentation.ViewModelContract
import com.dzhafarov.expense.domain.use_case.DeleteExpenseByIdUseCase
import com.dzhafarov.expense.domain.use_case.ObserveExpensesUseCase
import com.dzhafarov.expense.presentation.mapper.ExpenseMapper
import com.dzhafarov.filters.presentation.ObserveAppliedFiltersUseCase
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
    private val observeExpensesUseCase: ObserveExpensesUseCase,
    private val observeAppliedFiltersUseCase: ObserveAppliedFiltersUseCase,
    private val deleteExpenseByIdUseCase: DeleteExpenseByIdUseCase,
    private val expenseMapper: ExpenseMapper
) : ViewModel(), ViewModelContract<HomeUiState, HomeEvent, HomeUiAction> {

    private val _state = MutableStateFlow(HomeUiState())
    override val state: StateFlow<HomeUiState> = _state.asStateFlow()

    private val _events = Channel<HomeEvent>()
    override val events: Flow<HomeEvent> = _events.receiveAsFlow()

    private val _itemsBeingRemoved = MutableStateFlow(setOf<Long>())

    init {
        loadStrings()
        observeExpenses()
    }

    override fun reduce(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnAddExpenseClick -> onAddExpenseClick()
            is HomeUiAction.OnExpenseEditClick -> onExpenseEditClicked(action.id)
            is HomeUiAction.OnExpenseDeleteSwipe -> onExpenseDeleteSwiped(action.id)
            is HomeUiAction.OnViewLookingClick -> onViewLookingClick()
            is HomeUiAction.OnSettingsClick -> onOpenSettings()
            is HomeUiAction.OnFilterClick -> onFilterClick()
            is HomeUiAction.OnSearchClick -> onSearchClick()
            is HomeUiAction.OnDashboardClick -> onDashboardClick()
        }
    }

    private fun onOpenSettings() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenSettings)
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
                    emptyExpenses = stringProvider.noExpensesToFilterOut
                )
            )
        }
    }

    private fun onSearchClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenSearch)
        }
    }

    private fun onDashboardClick() {
        viewModelScope.launch {
            _events.send(HomeEvent.OpenDashboard)
        }
    }

    private fun onExpenseDeleteSwiped(id: Long) {
        val item = _state.value.expenses.find { it.id == id } ?: return
        _itemsBeingRemoved.update { it + setOf(id) }

        viewModelScope.launch {
            _events.send(
                HomeEvent.DeleteExpense(
                    message = stringProvider.expenseDeletedMessage,
                    actionLabel = stringProvider.undoDeleteButton,
                    onActionPerformed = {
                        _itemsBeingRemoved.update { it - setOf(id) }
                    },
                    onDismissed = {
                        deleteExpenseByIdUseCase.execute(item.id)
                    }
                )
            )
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    title = stringProvider.title,
                    emptyExpensesMessage = stringProvider.noExpensesYet,
                    addExpenseMessage = stringProvider.addExpense
                )
            }
        }
    }

    private fun observeExpenses() {
        viewModelScope.launch {
            observeExpensesUseCase.execute()
                .combine(_itemsBeingRemoved) { original, removing ->
                    original.filter {
                        it.id !in removing
                    }
                }
                .combine(observeAppliedFiltersUseCase.execute(), ::Pair)
                .onEach { (_, predicates) ->
                    _state.update {
                        it.copy(
                            isFilterEmpty = predicates.isEmpty(),
                            emptyExpensesMessage = if (predicates.isNotEmpty()) {
                                stringProvider.noExpensesMatchTheGivenFilter
                            } else {
                                stringProvider.noExpensesYet
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