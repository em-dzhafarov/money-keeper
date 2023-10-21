package com.dzhafarov.moneykeeper.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.moneykeeper.core.domain.use_case.execute
import com.dzhafarov.moneykeeper.expense.domain.use_case.GetExpensesUseCase
import com.dzhafarov.moneykeeper.profile.use_case.GetCurrentUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val stringProvider: HomeStringProvider,
    private val getCurrentUserProfileUseCase: GetCurrentUserProfileUseCase,
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiAction: MutableSharedFlow<HomeAction> = MutableSharedFlow()
    val uiAction: SharedFlow<HomeAction> = _uiAction.asSharedFlow()

    init {
        loadWelcomeMessage()
        loadStrings()
        loadExpenses()
    }

    fun onAddExpenseClick() {
        viewModelScope.launch {
            _uiAction.emit(HomeAction.AddExpense)
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
                    emptyExpensesMessage = stringProvider.noExpensesYet(),
                    addExpenseMessage = stringProvider.addExpense()
                )
            }
        }
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    expenses = getExpensesUseCase.execute()
                )
            }
        }
    }
}