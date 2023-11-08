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
class TimeSelectorViewModel @Inject constructor(
    private val stringProvider: DateTimeSelectorStringProvider
) : ViewModel() {

    private val _state = MutableStateFlow(TimeSelectorUiState())
    val state: StateFlow<TimeSelectorUiState> = _state.asStateFlow()

    private val _events = Channel<TimeSelectorEvent>()
    val events: Flow<TimeSelectorEvent> = _events.receiveAsFlow()

    init {
        loadStrings()
    }

    fun initializeDefaults(hours: Int?, minutes: Int?) {
        val (hour, minute) = if (hours != null && minutes != null) {
            hours to minutes
        } else {
            Timestamp.now().let { it.hours to it.minutes }
        }

        _state.update {
            it.copy(
                hour = hour,
                minute = minute
            )
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _events.send(TimeSelectorEvent.NavigateBack)
        }
    }

    fun onTimeSelected(data: Pair<Int, Int>) {
        viewModelScope.launch {
            _events.send(TimeSelectorEvent.Result(data))
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    title = stringProvider.timeTitle(),
                    cancel = stringProvider.cancelButton(),
                    confirm = stringProvider.confirmButton()
                )
            }
        }
    }
}