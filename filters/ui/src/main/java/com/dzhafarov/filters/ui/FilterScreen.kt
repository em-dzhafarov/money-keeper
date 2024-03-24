package com.dzhafarov.filters.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.filters.presentation.FilterViewModel
import com.dzhafarov.filters.ui.content.FilterContent
import com.dzhafarov.filters.ui.events.FilterEvents

@Composable
fun FilterScreen(navController: NavController) {
    val viewModel: FilterViewModel = hiltViewModel()

    FilterEvents(
        events = viewModel.events,
        navController = navController
    )

    FilterContent(
        state = viewModel.state,
        onAction = viewModel::reduce
    )
}
