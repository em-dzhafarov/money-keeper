package com.dzhafarov.about_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class AboutAppViewModel @Inject constructor(
    private val stringProvider: AboutAppStringProvider
) : ViewModel(), ViewModelContract<AboutAppUiState, AboutAppEvent, AboutAppUiAction> {

    private val _state = MutableStateFlow(AboutAppUiState())
    override val state: StateFlow<AboutAppUiState> = _state.asStateFlow()

    private val _events = Channel<AboutAppEvent>()
    override val events: Flow<AboutAppEvent> = _events.receiveAsFlow()

    init {
        loadStrings()
    }

    override fun reduce(action: AboutAppUiAction) {
        when (action) {
            is AboutAppUiAction.OnDismiss -> onDismiss()
        }
    }

    private fun onDismiss() {
        viewModelScope.launch {
            _events.send(AboutAppEvent.Close)
        }
    }

    private fun loadStrings() {
        _state.update {
            it.copy(
                title = stringProvider.title,
                text = stringProvider.text,
                confirm = stringProvider.confirm
            )
        }
    }
}
