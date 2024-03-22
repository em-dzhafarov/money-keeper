package com.dzhafarov.settings.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.settings.presentation.SettingsViewModel
import com.dzhafarov.settings.ui.content.ScreenContent
import com.dzhafarov.settings.ui.events.ScreenEvents

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
