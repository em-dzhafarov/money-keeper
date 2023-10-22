package com.dzhafarov.moneykeeper.expense.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class AddExpenseViewModel @Inject constructor(
    private val stringProvider: AddExpenseStringProvider
) : ViewModel() {

    private val _uiAction = MutableSharedFlow<AddExpenseAction>()
    val uiAction: SharedFlow<AddExpenseAction> = _uiAction.asSharedFlow()

    private val _uiState = MutableStateFlow(AddExpenseUiState())
    val uiState: StateFlow<AddExpenseUiState> = _uiState.asStateFlow()

    init {
        loadStrings()
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _uiAction.emit(AddExpenseAction.NavigateBack)
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    title = stringProvider.title()
                )
            }
        }
    }
}