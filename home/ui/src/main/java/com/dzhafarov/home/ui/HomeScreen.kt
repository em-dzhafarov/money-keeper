package com.dzhafarov.home.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.home.presentation.HomeViewModel
import com.dzhafarov.home.ui.content.ScreenContent
import com.dzhafarov.home.ui.events.ScreenEvents

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val snackbarHostState = remember { SnackbarHostState() }

    ScreenEvents(
        events = viewModel.events,
        navController = navController,
        snackbarHostState = snackbarHostState
    )

    ScreenContent(
        state = viewModel.state,
        snackbarHostState = snackbarHostState,
        navController = navController,
        onAction = viewModel::reduce
    )
}
