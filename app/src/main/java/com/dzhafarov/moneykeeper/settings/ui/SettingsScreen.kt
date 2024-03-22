package com.dzhafarov.moneykeeper.settings.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.settings.presentation.SettingsViewModel
import com.dzhafarov.moneykeeper.settings.ui.content.ScreenContent
import com.dzhafarov.moneykeeper.settings.ui.events.ScreenEvents

@Composable
fun SettingsScreen(navController: NavController) {
    val viewModel: SettingsViewModel = hiltViewModel()

    ScreenEvents(
        events = viewModel.events,
        navController = navController
    )

    ScreenContent(
        state = viewModel.state,
        onAction = viewModel::reduce
    )
}
