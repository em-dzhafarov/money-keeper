package com.dzhafarov.moneykeeper.date_time.ui.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.date_time.presentation.time.TimeViewModel

object TimeSelector {
    const val SELECTED_TIME_RESULT = "selected_time_result"
    const val SELECTED_HOUR_ARG = "selected_hour_arg"
    const val SELECTED_MINUTE_ARG = "selected_minute_arg"
}

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
