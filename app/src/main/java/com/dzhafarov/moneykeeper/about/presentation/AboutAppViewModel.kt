package com.dzhafarov.moneykeeper.about.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class AboutAppViewModel @Inject constructor(
    private val stringProvider: AboutAppStringProvider
) : ViewModel() {

    private val _state = MutableStateFlow(AboutAppUiState())
    val state: StateFlow<AboutAppUiState> = _state.asStateFlow()

    private val _events = Channel<AboutAppEvent>()
    val events: Flow<AboutAppEvent> = _events.receiveAsFlow()

    init {
        loadStrings()
    }

    fun onDismiss() {
        viewModelScope.launch {
            _events.send(AboutAppEvent.Close)
        }
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    title = stringProvider.title(),
                    text = stringProvider.text(),
                    confirm = stringProvider.confirm()
                )
            }
        }
    }
}