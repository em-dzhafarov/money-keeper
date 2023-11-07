package com.dzhafarov.moneykeeper.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.R
import com.dzhafarov.moneykeeper.core.ui.BaseTopBar
import com.dzhafarov.moneykeeper.core.ui.Destination
import com.dzhafarov.moneykeeper.core.utils.collectAsEffect
import com.dzhafarov.moneykeeper.core.utils.isScrollingUp
import com.dzhafarov.moneykeeper.core.utils.navigateTo
import com.dzhafarov.moneykeeper.expense.presentation.ExpenseItem
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
    val snackbarHostState = remember { SnackbarHostState() }

    HomeActions(
        actions = viewModel.uiAction,
        navController = navController,
        snackbarHostState = snackbarHostState
    )

    HomeUiContent(
        uiState = uiState,
        onAddExpenseClick = viewModel::onAddExpenseClick,
        onHomeClick = viewModel::onHomeClick,
        onNotificationsClick = viewModel::onNotificationsClick,
        onEditClicked = viewModel::onExpenseEditClicked,
        onDeleteSwiped = viewModel::onExpenseDeleteSwiped,
        onViewLookingClick = viewModel::onViewLookingClick,
        onFilterClick = viewModel::onFilterClick,
        onSearchClick = viewModel::onSearchClick,
        snackbarHostState = snackbarHostState
    )
}

@Composable
private fun HomeActions(
    actions: Flow<HomeAction>,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    actions.collectAsEffect { action ->
        when (action) {
            is HomeAction.AddExpense -> {
                navController.navigateTo(
                    destination = Destination.Screen.AddExpense,
                    args = listOf(0L)
                )
            }

            is HomeAction.EditExpense -> {
                navController.navigateTo(
                    destination = Destination.Screen.AddExpense,
                    args = listOf(action.id)
                )
            }

            is HomeAction.OpenNotifications -> {
                navController.navigateTo(Destination.Screen.Notifications)
            }

            is HomeAction.OpenAboutAppInfo -> {
                navController.navigateTo(Destination.Dialog.AboutApp)
            }

            is HomeAction.DeleteExpense -> {
                val result = snackbarHostState.showSnackbar(
                    message = action.message,
                    actionLabel = action.actionLabel,
                    withDismissAction = true,
                    duration = SnackbarDuration.Long
                )

                when (result) {
                    SnackbarResult.ActionPerformed -> action.onActionPerformed()
                    SnackbarResult.Dismissed -> action.onDismissed()
                }
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
    onNotificationsClick: () -> Unit,
    onEditClicked: (Long) -> Unit,
    onDeleteSwiped: (ExpenseItem) -> Unit,
    onViewLookingClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val scrollState = rememberLazyListState()
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topBarState)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                title = {
                    val fraction = topBarState.collapsedFraction

                    AnimatedVisibility(
                        visible = fraction > 0.5f,
                        enter = expandIn(expandFrom = Alignment.TopStart),
                        exit = shrinkOut()
                    ) {
                        Text(text = uiState.title)
                    }

                    AnimatedVisibility(
                        visible = fraction <= 0.5f,
                        enter = expandIn(),
                        exit = shrinkOut()
                    ) {
                        Text(
                            text = uiState.welcomeMessage,
                        )
                    }
                },
                navigationIcon = Icons.Default.Home,
                scrollingBehavior = scrollBehavior,
                onNavigationIconPressed = onHomeClick,
                isLarge = true,
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
        ExpensesContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            scrollState = scrollState,
            emptyExpensesMessage = uiState.emptyExpensesMessage,
            editLabel = uiState.editExpenseLabel,
            paidByPrefix = uiState.paidByPrefix,
            expenses = uiState.expenses,
            onEditClicked = onEditClicked,
            onDeleteSwiped = onDeleteSwiped,
            onViewLookingClick = onViewLookingClick,
            onFilterClick = onFilterClick,
            onSearchClick = onSearchClick
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
        containerColor = MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        ),
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
        containerColor = MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        ),
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
        tint = MaterialTheme.colorScheme.onPrimary
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ExpensesContent(
    expenses: List<ExpenseItem>,
    emptyExpensesMessage: String,
    editLabel: String,
    paidByPrefix: String,
    onEditClicked: (Long) -> Unit,
    onDeleteSwiped: (ExpenseItem) -> Unit,
    onViewLookingClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = if (expenses.isEmpty()) {
            Arrangement.Center
        } else {
            Arrangement.Top
        },
        horizontalAlignment = if (expenses.isEmpty()) {
            Alignment.CenterHorizontally
        } else {
            Alignment.Start
        },
        contentPadding = PaddingValues(16.dp)
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
            item {
                ExpenseHeaderContent(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    onViewLookingClick = onViewLookingClick,
                    onFilterClick = onFilterClick,
                    onSearchClick = onSearchClick
                )
            }

            itemsIndexed(expenses, key = { _, item -> item.id }) { index, item ->
                val dismissState = rememberDismissState()
                var lastDismissedItemId by remember { mutableLongStateOf(-1L) }

                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    if (lastDismissedItemId != item.id) {
                        onDeleteSwiped(item)
                        lastDismissedItemId = item.id
                    } else {
                        LaunchedEffect(Unit) {
                            dismissState.snapTo(DismissValue.Default)
                        }
                    }
                }

                if (dismissState.currentValue == DismissValue.Default) {
                    lastDismissedItemId = -1L
                }

                if (dismissState.dismissDirection == DismissDirection.EndToStart) {
                    val haptic = LocalHapticFeedback.current
                    LaunchedEffect(Unit) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                }

                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier.animateItemPlacement(tween(durationMillis = 500)),
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        val backgroundColor by animateColorAsState(
                            targetValue = when (dismissState.targetValue) {
                                DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.8f)
                                else -> Color.White
                            },
                            label = ""
                        )

                        val iconScale by animateFloatAsState(
                            targetValue = if (dismissState.targetValue == DismissValue.DismissedToStart) {
                                1.3f
                            } else {
                                0.5f
                            },
                            label = ""
                        )

                        Box(
                            Modifier
                                .clip(CardDefaults.shape)
                                .fillMaxSize()
                                .background(color = backgroundColor)
                                .padding(end = 16.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                modifier = Modifier.scale(iconScale),
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    dismissContent = {
                        ExpenseItemContent(
                            modifier = Modifier.fillMaxWidth(),
                            item = item,
                            editLabel = editLabel,
                            paidByPrefix = paidByPrefix,
                            onEditClicked = onEditClicked
                        )
                    }
                )

                if (index != expenses.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun ExpenseHeaderContent(
    onViewLookingClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = onViewLookingClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.List,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onFilterClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        IconButton(onClick = onSearchClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
private fun ExpenseItemContent(
    item: ExpenseItem,
    editLabel: String,
    paidByPrefix: String,
    onEditClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = item.reason.resourceId),
                    contentDescription = item.reason.title,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = item.reason.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Row(
                        modifier = Modifier.offset(x = (-3).dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = item.currency.iconRes),
                            contentDescription = item.currency.code,
                            tint = MaterialTheme.colorScheme.tertiary
                        )

                        Text(
                            text = item.amount,
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.tertiary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onEditClicked(item.id) }
                        .padding(4.dp),
                    text = editLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = TextDecoration.Underline
                )
            }

            if (item.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = buildAnnotatedString {
                        append(paidByPrefix)
                        pushStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        append(item.method.title)
                        pop()
                    },
                    style = MaterialTheme.typography.bodyMedium
                )

                Icon(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(20.dp),
                    painter = painterResource(id = item.method.resourceId),
                    contentDescription = item.method.title,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = item.time,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}