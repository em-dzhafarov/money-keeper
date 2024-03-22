package com.dzhafarov.date_time.ui.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.date_time.presentation.TimeViewModel

@Composable
fun TimeSelectorDialog(
    navController: NavController,
    hours: Int?,
    minutes: Int?
) {
    val viewModel: TimeViewModel = hiltViewModel()

    LaunchedEffect(hours, minutes) {
        viewModel.initializeDefaults(hours, minutes)
    }

    TimeSelectorEvents(
        events = viewModel.events,
        navController = navController
    )

    TimeSelectorContent(
        state = viewModel.state,
        onAction = viewModel::reduce
    )
}
