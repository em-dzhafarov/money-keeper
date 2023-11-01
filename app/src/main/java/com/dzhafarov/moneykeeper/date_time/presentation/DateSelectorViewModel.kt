package com.dzhafarov.moneykeeper.date_time.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.moneykeeper.date_time.domain.Timestamp
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
class DateSelectorViewModel @Inject constructor(
    private val stringProvider: DateTimeSelectorStringProvider
) : ViewModel() {

    private val _uiAction = MutableSharedFlow<DateSelectorUiAction>()
    val uiAction: SharedFlow<DateSelectorUiAction> = _uiAction.asSharedFlow()

    private val _uiState = MutableStateFlow(DateSelectorUiState())
    val uiState: StateFlow<DateSelectorUiState> = _uiState.asStateFlow()

    init {
        loadStrings()
    }

    fun initializeDefaults(millis: Long?) {
        _uiState.update {
            it.copy(
                current = millis ?: Timestamp.now().milliseconds
            )
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _uiAction.emit(DateSelectorUiAction.NavigateBack)
        }
    }

    fun onDateSelected(millis: Long) {
        viewModelScope.launch {
            _uiAction.emit(DateSelectorUiAction.Result(millis))
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    title = stringProvider.dateTitle(),
                    cancel = stringProvider.cancelButton(),
                    confirm = stringProvider.confirmButton()
                )
            }
        }
    }
}