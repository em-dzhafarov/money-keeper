package com.dzhafarov.date_time.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.core.presentation.ViewModelContract
import com.dzhafarov.date_time.domain.Timestamp
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
class DateViewModel @Inject constructor(
    private val stringProvider: DateTimeSelectorStringProvider
) : ViewModel(), ViewModelContract<DateUiState, DateEvent, DateUiAction> {

    private val _state = MutableStateFlow(DateUiState())
    override val state: StateFlow<DateUiState> = _state.asStateFlow()

    private val _events = Channel<DateEvent>()
    override val events: Flow<DateEvent> = _events.receiveAsFlow()

    init {
        loadStrings()
    }

    override fun reduce(action: DateUiAction) {
        when (action) {
            is DateUiAction.OnDateSelected -> onDateSelected(action.millis)
            is DateUiAction.OnDismiss -> onBackPressed()
        }
    }

    fun initializeDefaults(millis: Long?) {
        _state.update {
            it.copy(
                current = millis ?: Timestamp.now().milliseconds
            )
        }
    }

    private fun onBackPressed() {
        viewModelScope.launch {
            _events.send(DateEvent.NavigateBack)
        }
    }

    private fun onDateSelected(millis: Long) {
        viewModelScope.launch {
            _events.send(DateEvent.Result(millis))
        }
    }

    private fun loadStrings() {
        _state.update {
            it.copy(
                title = stringProvider.dateTitle,
                cancel = stringProvider.cancelButton,
                confirm = stringProvider.confirmButton
            )
        }
    }
}
