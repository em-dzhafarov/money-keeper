package com.dzhafarov.moneykeeper.date_time.ui.date

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.date_time.presentation.date.DateViewModel

object DateSelector {
    const val SELECTED_DATE_RESULT = "selected_date_result"
    const val SELECTED_DATE_ARG = "selected_date_arg"
}

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
