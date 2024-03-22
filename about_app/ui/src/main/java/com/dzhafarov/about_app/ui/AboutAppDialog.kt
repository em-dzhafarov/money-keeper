package com.dzhafarov.about_app.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.about_app.presentation.AboutAppViewModel
import com.dzhafarov.about_app.ui.content.ScreenContent
import com.dzhafarov.about_app.ui.events.ScreenEvents

@Composable
fun AboutAppDialog(navController: NavController) {
    val viewModel: AboutAppViewModel = hiltViewModel()

    ScreenEvents(
        events = viewModel.events,
        navController = navController
    )

    ScreenContent(
        state = viewModel.state,
        onAction = viewModel::reduce
    )
}
