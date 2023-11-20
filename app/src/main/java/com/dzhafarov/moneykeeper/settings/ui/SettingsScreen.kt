package com.dzhafarov.moneykeeper.settings.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.core.ui.BaseTopBar
import com.dzhafarov.moneykeeper.core.ui.Destination
import com.dzhafarov.moneykeeper.core.utils.collectAsEffect
import com.dzhafarov.moneykeeper.settings.presentation.SettingsEvent
import com.dzhafarov.moneykeeper.settings.presentation.SettingsUiState
import com.dzhafarov.moneykeeper.settings.presentation.SettingsViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    SettingsEvents(
        events = viewModel.events,
        navController = navController
    )

    SettingsContent(
        state = state,
        onBackPressed = viewModel::onNavigateBack,
        onAppearanceClick = viewModel::onAppearanceClick,
        onDynamicThemeChecked = viewModel::onDynamicThemeCheckChanged,
        onLightThemeSelected = viewModel::onLightThemeSelected,
        onDarkThemeSelected = viewModel::onDarkThemeSelected,
        onSystemThemeSelected = viewModel::onSystemThemeSelected,
        onSelectThemeClick = viewModel::onSelectThemeClick
    )
}

@Composable
private fun SettingsEvents(
    events: Flow<SettingsEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is SettingsEvent.NavigateBack -> {
                navController.popBackStack()
            }

            is SettingsEvent.NavigateHome -> {
                navController.popBackStack(
                    route = Destination.Screen.Root.Home.route,
                    inclusive = false
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent(
    state: SettingsUiState,
    onBackPressed: () -> Unit,
    onAppearanceClick: () -> Unit,
    onDynamicThemeChecked: (Boolean) -> Unit,
    onSelectThemeClick: () -> Unit,
    onLightThemeSelected: () -> Unit,
    onDarkThemeSelected: () -> Unit,
    onSystemThemeSelected: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BaseTopBar(
                title = { Text(text = state.title) },
                onNavigationIconPressed = onBackPressed
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAppearanceClick.invoke() }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.appearanceAndLookingLabel,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize * 1.2f
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = if (state.isAppearanceSectionVisible) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = null
                )
            }

            AnimatedVisibility(
                visible = state.isAppearanceSectionVisible,
                enter = expandIn(expandFrom = Alignment.CenterStart),
                exit = shrinkOut(shrinkTowards = Alignment.CenterStart)
            ) {
                Column {
                    if (state.isDynamicColorsVisible) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onDynamicThemeChecked.invoke(state.isDynamicTheme.not()) }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = state.dynamicColorsLabel,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Switch(
                                checked = state.isDynamicTheme,
                                onCheckedChange = null
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectThemeClick.invoke() }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = state.selectThemeLabel,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = if (state.isThemeItemsVisible) {
                                Icons.Default.KeyboardArrowUp
                            } else {
                                Icons.Default.KeyboardArrowDown
                            },
                            contentDescription = null
                        )
                    }

                    AnimatedVisibility(
                        visible = state.isThemeItemsVisible,
                        enter = expandIn(expandFrom = Alignment.CenterStart),
                        exit = shrinkOut(shrinkTowards = Alignment.CenterStart)
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onLightThemeSelected.invoke() }
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = state.lightThemeLabel,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                RadioButton(
                                    selected = state.isLightTheme,
                                    onClick = null
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onDarkThemeSelected.invoke() }
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = state.darkThemeLabel,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                RadioButton(
                                    selected = state.isDarkTheme,
                                    onClick = null
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSystemThemeSelected.invoke() }
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = state.systemThemeLabel,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                RadioButton(
                                    selected = state.isSystemTheme,
                                    onClick = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}