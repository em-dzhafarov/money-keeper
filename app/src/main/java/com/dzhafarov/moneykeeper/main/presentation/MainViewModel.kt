package com.dzhafarov.moneykeeper.main.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.core.ui.utils.isDarkTheme
import com.dzhafarov.settings.domain.use_case.GetSelectedThemeUseCase
import com.dzhafarov.settings.domain.use_case.ObserveSelectedThemeUseCase
import com.dzhafarov.settings.domain.model.Theme
import com.dzhafarov.settings.domain.model.ThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val getSelectedThemeUseCase: GetSelectedThemeUseCase,
    private val observeSelectedThemeUseCase: ObserveSelectedThemeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    init {
        initialize(context)
    }

    private fun initialize(context: Context) {
        viewModelScope.launch {
            delay(START_UP_SPLASH_DELAY)

            _state.update {
                it.copy(
                    isSplashShown = false
                )
            }
        }

        observeSelectedThemeUseCase.execute()
            .onEach { theme ->
                updateTheme(
                    theme = theme,
                    context = context
                )
            }
            .launchIn(viewModelScope)
    }

    fun invalidateThemeIfNeeded(context: Context) {
        viewModelScope.launch {
            updateTheme(
                theme = getSelectedThemeUseCase.execute(),
                context = context
            )
        }
    }

    private fun updateTheme(theme: Theme, context: Context) {
        _state.update {
            it.copy(
                isDarkTheme = theme.type == ThemeType.DARK ||
                        theme.type == ThemeType.SYSTEM && context.isDarkTheme,
                isDynamicTheme = theme.isDynamic
            )
        }
    }

    private companion object {
        const val START_UP_SPLASH_DELAY = 800L
    }
}
