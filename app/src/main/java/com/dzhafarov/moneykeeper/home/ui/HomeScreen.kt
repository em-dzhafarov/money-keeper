package com.dzhafarov.moneykeeper.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.core.ui.Destination
import com.dzhafarov.moneykeeper.core.ui.BaseTopBar
import com.dzhafarov.moneykeeper.core.utils.collectAsEffect
import com.dzhafarov.moneykeeper.core.utils.isScrollingUp
import com.dzhafarov.moneykeeper.core.utils.navigateTo
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import com.dzhafarov.moneykeeper.home.presentation.HomeAction
import com.dzhafarov.moneykeeper.home.presentation.HomeUiState
import com.dzhafarov.moneykeeper.home.presentation.HomeViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsState()

    HomeActions(
        actions = viewModel.uiAction,
        navController = navController
    )

    HomeUiContent(
        uiState = uiState,
        onAddExpenseClick = viewModel::onAddExpenseClick,
        onHomeClick = viewModel::onHomeClick,
        onNotificationsClick = viewModel::onNotificationsClick
    )
}

@Composable
private fun HomeActions(
    actions: Flow<HomeAction>,
    navController: NavController
) {
    actions.collectAsEffect { action ->
        when (action) {
            is HomeAction.AddExpense -> {
                navController.navigateTo(Destination.Screen.AddExpense)
            }
            is HomeAction.OpenNotifications -> {
                navController.navigateTo(Destination.Screen.Notifications)
            }
            is HomeAction.OpenAboutAppInfo -> {
                navController.navigateTo(Destination.Dialog.AboutApp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeUiContent(
    uiState: HomeUiState,
    onAddExpenseClick: () -> Unit,
    onHomeClick: () -> Unit,
    onNotificationsClick: () -> Unit
) {
    val scrollState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FabContent(
                state = scrollState,
                onClick = onAddExpenseClick,
                title = uiState.addExpenseMessage
            )
        },
        topBar = {
            BaseTopBar(
                modifier = Modifier.fillMaxWidth(),
                title = uiState.title,
                navigationIcon = Icons.Default.Home,
                onNavigationIconPressed = onHomeClick,
                actions = {
                    IconButton(onClick = onNotificationsClick) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        MainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            scrollState = scrollState,
            welcomeMessage = uiState.welcomeMessage,
            emptyExpensesMessage = uiState.emptyExpensesMessage,
            expenses = uiState.expenses
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
    welcomeMessage: String,
    emptyExpensesMessage: String,
    expenses: List<Expense>
) {
    Column(
        modifier = modifier
    ) {
        GreetingMessage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            message = welcomeMessage
        )

        ExpensesContent(
            expenses = expenses,
            emptyExpensesMessage = emptyExpensesMessage,
            scrollState = scrollState,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun FabContent(
    state: LazyListState,
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        val isSmallFab = state.isScrollingUp().not()

        AnimatedVisibility(visible = isSmallFab) {
            SmallAddFab(
                onClick = onClick
            )
        }

        AnimatedVisibility(visible = !isSmallFab) {
            ExtendedAddFab(
                title = title,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun SmallAddFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = { AddIcon() }
    )
}

@Composable
private fun ExtendedAddFab(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddIcon()

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}

@Composable
private fun AddIcon() {
    Icon(
        imageVector = Icons.Outlined.Add,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.surfaceTint
    )
}

@Composable
private fun GreetingMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = message,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun ExpensesContent(
    expenses: List<Expense>,
    emptyExpensesMessage: String,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (expenses.isEmpty()) {
            item {
                Text(
                    text = emptyExpensesMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            items(expenses) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
