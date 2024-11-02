package com.dzhafarov.date_time.presentation

import com.dzhafarov.core.presentation.ViewModelContract
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
class TimeViewModel @Inject constructor(
    private val stringProvider: DateTimeSelectorStringProvider
) : ViewModelContract<TimeUiState, TimeEvent, TimeUiAction>() {

    private val _state = MutableStateFlow(TimeUiState())
    override val state: StateFlow<TimeUiState> = _state.asStateFlow()

    private val _events = Channel<TimeEvent>()
    override val events: Flow<TimeEvent> = _events.receiveAsFlow()

    init {
        loadStrings()
    }

    fun initializeDefaults(hours: Int?, minutes: Int?) {
        val (hour, minute) = if (hours != null && minutes != null) {
            hours to minutes
        } else {
            com.dzhafarov.date_time.domain.Timestamp.now().let { it.hours to it.minutes }
        }

        _state.update {
            it.copy(
                hour = hour,
                minute = minute
            )
        }
    }

    override fun reduce(action: TimeUiAction) {
        when (action) {
            is TimeUiAction.OnDismiss -> onBackPressed()
            is TimeUiAction.OnTimeSelected -> onTimeSelected(action.hour, action.minute)
        }
    }

    private fun onBackPressed() {
        launch {
            _events.send(TimeEvent.NavigateBack)
        }
    }

    private fun onTimeSelected(hour: Int, minute: Int) {
        launch {
            _events.send(TimeEvent.Result(hour to minute))
        }
    }

    private fun loadStrings() {
        _state.update {
            it.copy(
                title = stringProvider.timeTitle,
                cancel = stringProvider.cancelButton,
                confirm = stringProvider.confirmButton
            )
        }
    }
}
