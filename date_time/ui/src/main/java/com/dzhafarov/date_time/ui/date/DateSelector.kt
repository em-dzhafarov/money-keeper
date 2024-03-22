package com.dzhafarov.date_time.ui.date

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.date_time.presentation.DateViewModel

@Composable
fun DateSelectorDialog(
    navController: NavController,
    initial: Long?
) {
    val viewModel: DateViewModel = hiltViewModel()

    LaunchedEffect(initial) {
        viewModel.initializeDefaults(initial)
    }

    DateSelectorEvents(
        events = viewModel.events,
        navController = navController
    )

    DateSelectorContent(
        state = viewModel.state,
        onAction = viewModel::reduce
    )
}
