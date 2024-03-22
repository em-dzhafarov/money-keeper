package com.dzhafarov.settings.ui.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.settings.presentation.SettingsUiAction
import com.dzhafarov.settings.presentation.SettingsUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun ScreenContent(
    state: StateFlow<SettingsUiState>,
    onAction: (SettingsUiAction) -> Unit
) {
    val uiState by state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarContent(
                title = uiState.title,
                onNavClick = { onAction(SettingsUiAction.OnNavigateBack) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            AppearanceContent(
                label = uiState.appearanceAndLookingLabel,
                isExpanded = uiState.isAppearanceSectionVisible,
                onClick = { onAction(SettingsUiAction.OnAppearanceClick) }
            )

            AnimatedVisibility(
                visible = uiState.isAppearanceSectionVisible,
                enter = expandIn(expandFrom = Alignment.CenterStart),
                exit = shrinkOut(shrinkTowards = Alignment.CenterStart)
            ) {
                Column {
                    if (uiState.isDynamicColorsVisible) {
                        DynamicColorsContent(
                            label = uiState.dynamicColorsLabel,
                            isChecked = uiState.isDynamicTheme,
                            onCheckedChanged = {
                                onAction(SettingsUiAction.OnDynamicThemeCheckChanged(it))
                            }
                        )
                    }

                    SelectThemeContent(
                        label = uiState.selectThemeLabel,
                        isExpanded = uiState.isThemeItemsVisible,
                        onClick = { onAction(SettingsUiAction.OnSelectThemeClick) }
                    )

                    AnimatedVisibility(
                        visible = uiState.isThemeItemsVisible,
                        enter = expandIn(expandFrom = Alignment.CenterStart),
                        exit = shrinkOut(shrinkTowards = Alignment.CenterStart)
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            ThemeItemContent(
                                label = uiState.lightThemeLabel,
                                isSelected = uiState.isLightTheme,
                                onClick = { onAction(SettingsUiAction.OnLightThemeSelected) }
                            )

                            ThemeItemContent(
                                label = uiState.darkThemeLabel,
                                isSelected = uiState.isDarkTheme,
                                onClick = { onAction(SettingsUiAction.OnDarkThemeSelected) }
                            )

                            ThemeItemContent(
                                label = uiState.systemThemeLabel,
                                isSelected = uiState.isSystemTheme,
                                onClick = { onAction(SettingsUiAction.OnSystemThemeSelected) }
                            )
                        }
                    }
                }
            }
        }
    }
}
