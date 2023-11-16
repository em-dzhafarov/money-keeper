package com.dzhafarov.moneykeeper.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var isSplashShown: Boolean = true
        private set

    init {
        viewModelScope.launch {
            delay(START_UP_SPLASH_DELAY)
            isSplashShown = false
        }
    }

    private companion object {
        const val START_UP_SPLASH_DELAY = 800L
    }
}
