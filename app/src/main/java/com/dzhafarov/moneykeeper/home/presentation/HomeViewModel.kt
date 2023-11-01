package com.dzhafarov.moneykeeper.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.moneykeeper.core.domain.use_case.execute
import com.dzhafarov.moneykeeper.expense.domain.use_case.ObserveExpensesUseCase
import com.dzhafarov.moneykeeper.profile.use_case.GetCurrentUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val stringProvider: HomeStringProvider,
    private val getCurrentUserProfileUseCase: GetCurrentUserProfileUseCase,
    private val observeExpensesUseCase: ObserveExpensesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiAction: MutableSharedFlow<HomeAction> = MutableSharedFlow()
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
                    addExpenseMessage = stringProvider.addExpense()
                )
            }
        }
    }

    private fun observeExpenses() {
        viewModelScope.launch {
            observeExpensesUseCase.execute()
                .onEach { items ->
                    _uiState.update {
                        it.copy(
                            expenses = items
                        )
                    }
                }
                .launchIn(this)
        }
    }
}