package com.dzhafarov.expense.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.expense.presentation.ExpenseViewModel

@Composable
fun ExpenseScreen(
    navController: NavController,
    expenseId: Long = 0
) {
    val viewModel: ExpenseViewModel = hiltViewModel()

    LaunchedEffect(expenseId) {
        viewModel.initializeExpenseIfNeeded(expenseId)
    }

    ExpenseEvents(
        events = viewModel.events,
        navController = navController
    )

    ExpenseContent(
        state = viewModel.state,
        onAction = viewModel::reduce
    )

    DateTimePickerResults(
        navController = navController,
        defaultDate = viewModel.initialDate,
        defaultTime = viewModel.initialTime,
        onAction = viewModel::reduce
    )
}
