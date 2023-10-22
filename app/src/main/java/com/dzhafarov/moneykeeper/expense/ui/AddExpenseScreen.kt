package com.dzhafarov.moneykeeper.expense.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.core.ui.BaseTopBar
import com.dzhafarov.moneykeeper.core.utils.collectAsEffect
import com.dzhafarov.moneykeeper.expense.ui.presentation.AddExpenseAction
import com.dzhafarov.moneykeeper.expense.ui.presentation.AddExpenseUiState
import com.dzhafarov.moneykeeper.expense.ui.presentation.AddExpenseViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun AddExpenseScreen(
    navController: NavController,
    viewModel: AddExpenseViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    AddExpenseActions(
        actions = viewModel.uiAction,
        navController = navController
    )

    AddExpenseUiContent(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onBackPressed = viewModel::onBackPressed
    )
}

@Composable
private fun AddExpenseActions(
    actions: Flow<AddExpenseAction>,
    navController: NavController
) {
    actions.collectAsEffect { action ->
        when (action) {
            is AddExpenseAction.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddExpenseUiContent(
    uiState: AddExpenseUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BaseTopBar(
                title = uiState.title,
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationIconPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        MainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Add New Expense", style = MaterialTheme.typography.headlineLarge)
    }
}