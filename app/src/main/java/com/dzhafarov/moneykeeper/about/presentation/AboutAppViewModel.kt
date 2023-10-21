package com.dzhafarov.moneykeeper.about.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutAppViewModel @Inject constructor(
    private val stringProvider: AboutAppStringProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(AboutAppUiState())
    val uiState: StateFlow<AboutAppUiState> = _uiState.asStateFlow()

    init {
        loadStrings()
    }

    private fun loadStrings() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    title = stringProvider.title(),
                    text = stringProvider.text(),
                    confirm = stringProvider.confirm()
                )
            }
        }
    }
}