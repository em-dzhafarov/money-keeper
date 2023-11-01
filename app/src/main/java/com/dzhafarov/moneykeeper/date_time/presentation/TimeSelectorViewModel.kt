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
class TimeSelectorViewModel @Inject constructor(
    private val stringProvider: DateTimeSelectorStringProvider
) : ViewModel() {

    private val _uiAction = MutableSharedFlow<TimeSelectorUiAction>()
    val uiAction: SharedFlow<TimeSelectorUiAction> = _uiAction.asSharedFlow()

    private val _uiState = MutableStateFlow(TimeSelectorUiState())
    val uiState: StateFlow<TimeSelectorUiState> = _uiState.asStateFlow()

    init {
        loadStrings()
    }

    fun initializeDefaults(hours: Int?, minutes: Int?) {
        val (hour, minute) = if (hours != null && minutes != null) {
            hours to minutes
        } else {
            Timestamp.now().let { it.hours to it.minutes }
        }

        _uiState.update {
            it.copy(
                hour = hour,
                minute = minute
            )
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _uiAction.emit(TimeSelectorUiAction.NavigateBack)
        }
    }

    fun onTimeSelected(data: Pair<Int, Int>) {
        viewModelScope.launch {
            _uiAction.emit(TimeSelectorUiAction.Result(data))
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    title = stringProvider.timeTitle(),
                    cancel = stringProvider.cancelButton(),
                    confirm = stringProvider.confirmButton()
                )
            }
        }
    }
}