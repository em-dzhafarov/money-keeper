package com.dzhafarov.moneykeeper.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.core.presentation.ViewModelContract
import com.dzhafarov.moneykeeper.settings.domain.use_case.GetSelectedThemeUseCase
import com.dzhafarov.moneykeeper.settings.domain.use_case.IsDynamicColorsSupportedUseCase
import com.dzhafarov.moneykeeper.settings.domain.model.ThemeType
import com.dzhafarov.moneykeeper.settings.domain.use_case.UpdateSelectedThemeUseCase
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
internal class SettingsViewModel @Inject constructor(
    private val stringProvider: SettingsStringProvider,
    private val getSelectedThemeUseCase: GetSelectedThemeUseCase,
    private val updateSelectedThemeUseCase: UpdateSelectedThemeUseCase,
    private val isDynamicColorsSupportedUseCase: IsDynamicColorsSupportedUseCase
) : ViewModel(), ViewModelContract<SettingsUiState, SettingsEvent, SettingsUiAction> {

    private val _state = MutableStateFlow(SettingsUiState())
    override val state: StateFlow<SettingsUiState> = _state.asStateFlow()

    private val _events = Channel<SettingsEvent>()
    override val events: Flow<SettingsEvent> = _events.receiveAsFlow()

    init {
        initialize()
    }

    override fun reduce(action: SettingsUiAction) {
        when (action) {
            is SettingsUiAction.OnAppearanceClick -> onAppearanceClick()
            is SettingsUiAction.OnDarkThemeSelected -> onDarkThemeSelected()
            is SettingsUiAction.OnDynamicThemeCheckChanged -> onDynamicThemeCheckChanged(action.isChecked)
            is SettingsUiAction.OnLightThemeSelected -> onLightThemeSelected()
            is SettingsUiAction.OnNavigateBack -> onNavigateBack()
            is SettingsUiAction.OnSelectThemeClick -> onSelectThemeClick()
            is SettingsUiAction.OnSystemThemeSelected -> onSystemThemeSelected()
        }
    }

    private fun onNavigateBack() {
        viewModelScope.launch {
            _events.send(SettingsEvent.NavigateBack)
        }
    }

    private fun onAppearanceClick() {
        _state.update {
            it.copy(
                isAppearanceSectionVisible = it.isAppearanceSectionVisible.not()
            )
        }
    }

    private fun onDynamicThemeCheckChanged(isChecked: Boolean) {
        _state.update {
            it.copy(
                isDynamicTheme = isChecked
            )
        }

        viewModelScope.launch {
            updateSelectedThemeUseCase.execute(
                getSelectedThemeUseCase.execute().copy(
                    isDynamic = isChecked
                )
            )
        }
    }

    private fun onSelectThemeClick() {
        _state.update {
            it.copy(
                isThemeItemsVisible = it.isThemeItemsVisible.not()
            )
        }
    }

    private fun onLightThemeSelected() {
        _state.update {
            it.copy(
                isLightTheme = true,
                isDarkTheme = false,
                isSystemTheme = false
            )
        }

        viewModelScope.launch {
            updateSelectedThemeUseCase.execute(
                getSelectedThemeUseCase.execute().copy(
                    type = ThemeType.LIGHT,
                )
            )
        }
    }

    private fun onDarkThemeSelected() {
        _state.update {
            it.copy(
                isLightTheme = false,
                isDarkTheme = true,
                isSystemTheme = false
            )
        }

        viewModelScope.launch {
            updateSelectedThemeUseCase.execute(
                getSelectedThemeUseCase.execute().copy(
                    type = ThemeType.DARK,
                )
            )
        }
    }

    private fun onSystemThemeSelected() {
        _state.update {
            it.copy(
                isLightTheme = false,
                isDarkTheme = false,
                isSystemTheme = true
            )
        }

        viewModelScope.launch {
            updateSelectedThemeUseCase.execute(
                getSelectedThemeUseCase.execute().copy(
                    type = ThemeType.SYSTEM,
                )
            )
        }
    }

    private fun initialize() {
        viewModelScope.launch {
            val theme = getSelectedThemeUseCase.execute()

            _state.update {
                it.copy(
                    title = stringProvider.title,
                    appearanceAndLookingLabel = stringProvider.appearanceAndLookingLabel,
                    selectThemeLabel = stringProvider.selectThemeLabel,
                    lightThemeLabel = stringProvider.lightThemeLabel,
                    darkThemeLabel = stringProvider.darkThemeLabel,
                    systemThemeLabel = stringProvider.systemThemeLabel,
                    dynamicColorsLabel = stringProvider.dynamicColorsLabel,
                    isDynamicColorsVisible = isDynamicColorsSupportedUseCase.execute(),
                    isDarkTheme = theme.type == ThemeType.DARK,
                    isLightTheme = theme.type == ThemeType.LIGHT,
                    isSystemTheme = theme.type == ThemeType.SYSTEM,
                    isDynamicTheme = theme.isDynamic
                )
            }
        }
    }
}
