package com.dzhafarov.moneykeeper.date_time.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.moneykeeper.date_time.domain.Timestamp
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
class DateSelectorViewModel @Inject constructor(
    private val stringProvider: DateTimeSelectorStringProvider
) : ViewModel() {

    private val _state = MutableStateFlow(DateSelectorUiState())
    val state: StateFlow<DateSelectorUiState> = _state.asStateFlow()

    private val _events = Channel<DateSelectorEvent>()
    val events: Flow<DateSelectorEvent> = _events.receiveAsFlow()

    init {
        loadStrings()
    }

    fun initializeDefaults(millis: Long?) {
        _state.update {
            it.copy(
                current = millis ?: Timestamp.now().milliseconds
            )
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _events.send(DateSelectorEvent.NavigateBack)
        }
    }

    fun onDateSelected(millis: Long) {
        viewModelScope.launch {
            _events.send(DateSelectorEvent.Result(millis))
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    title = stringProvider.dateTitle(),
                    cancel = stringProvider.cancelButton(),
                    confirm = stringProvider.confirmButton()
                )
            }
        }
    }
}