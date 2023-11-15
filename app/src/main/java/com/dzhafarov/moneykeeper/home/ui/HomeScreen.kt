package com.dzhafarov.moneykeeper.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.dzhafarov.moneykeeper.home.presentation.HomeEvent
import com.dzhafarov.moneykeeper.home.presentation.HomeUiState
import com.dzhafarov.moneykeeper.home.presentation.HomeViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    HomeEvents(
        events = viewModel.events,
        navController = navController,
        snackbarHostState = snackbarHostState
    )

    HomeContent(
        uiState = uiState,
        onAddExpenseClick = viewModel::onAddExpenseClick,
        onHomeClick = viewModel::onHomeClick,
        onEditClicked = viewModel::onExpenseEditClicked,
        onDeleteSwiped = viewModel::onExpenseDeleteSwiped,
        onViewLookingClick = viewModel::onViewLookingClick,
        onFilterClick = viewModel::onFilterClick,
        onSearchClick = viewModel::onSearchClick,
        snackbarHostState = snackbarHostState
    )
}

@Composable
private fun HomeEvents(
    events: Flow<HomeEvent>,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    events.collectAsEffect { event ->
        when (event) {
            is HomeEvent.AddExpense -> {
                navController.navigateTo(
                    destination = Destination.Screen.Expense,
                    args = listOf(0L)
                )
            }

            is HomeEvent.EditExpense -> {
                navController.navigateTo(
                    destination = Destination.Screen.Expense,
                    args = listOf(event.id)
                )
            }

            is HomeEvent.OpenNotifications -> {
                navController.navigateTo(Destination.Screen.Notifications)
            }

            is HomeEvent.OpenAboutAppInfo -> {
                navController.navigateTo(Destination.Dialog.AboutApp)
            }

            is HomeEvent.OpenFilter -> {

            }

            is HomeEvent.OpenSearch -> {

            }

            is HomeEvent.DeleteExpense -> {
                val result = snackbarHostState.showSnackbar(
                    message = event.message,
                    actionLabel = event.actionLabel,
                    withDismissAction = true,
                    duration = SnackbarDuration.Short
                )

                when (result) {
                    SnackbarResult.ActionPerformed -> event.onActionPerformed()
                    SnackbarResult.Dismissed -> event.onDismissed()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onAddExpenseClick: () -> Unit,
    onHomeClick: () -> Unit,
    onEditClicked: (Long) -> Unit,
    onDeleteSwiped: (Long) -> Unit,
    onViewLookingClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val lazyListState = rememberLazyListState()
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topBarState)
    val isGrid by remember(uiState.displayMode) { mutableStateOf(uiState.displayMode.isGrid()) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FabContent(
                isScrollingUp = if (isGrid) {
                    lazyStaggeredGridState.isScrollingUp()
                } else {
                    lazyListState.isScrollingUp()
                },
                onClick = onAddExpenseClick,
                title = uiState.addExpenseMessage
            )
        },
        topBar = {
            BaseTopBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    val isCollapsed = topBarState.collapsedFraction > 0.5f

                    AnimatedVisibility(
                        visible = isCollapsed,
                        enter = expandIn(),
                        exit = shrinkOut()
                    ) {
                        Text(text = uiState.title)
                    }

                    AnimatedVisibility(
                        visible = !isCollapsed,
                        enter = expandIn(),
                        exit = shrinkOut()
                    ) {
                        Text(text = uiState.welcomeMessage)
                    }
                },
                navigationIcon = Icons.Default.Home,
                scrollingBehavior = scrollBehavior,
                onNavigationIconPressed = onHomeClick,
                isLarge = true,
                actions = {
                    HeaderActionButtonsContent(
                        isGrid = isGrid,
                        onViewLookingClick = onViewLookingClick,
                        onFilterClick = onFilterClick,
                        onSearchClick = onSearchClick
                    )
                }
            )
        }
    ) { padding ->
        AnimatedVisibility(
            visible = uiState.expenses.isEmpty(),
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut(),
            content = {
                ExpensesEmptyContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    message = uiState.emptyExpensesMessage
                )
            }
        )

        AnimatedVisibility(
            visible = isGrid,
            enter = scaleIn(),
            exit = scaleOut(),
            content = {
                ExpensesGridContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    expenses = uiState.expenses,
                    scrollState = lazyStaggeredGridState,
                    onEditClicked = onEditClicked,
                    onDeleteSwiped = onDeleteSwiped
                )
            }
        )

        AnimatedVisibility(
            visible = isGrid.not(),
            enter = scaleIn(),
            exit = scaleOut(),
            content = {
                ExpensesListContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    scrollState = lazyListState,
                    editLabel = uiState.editExpenseLabel,
                    paidByPrefix = uiState.paidByPrefix,
                    expenses = uiState.expenses,
                    onEditClicked = onEditClicked,
                    onDeleteSwiped = onDeleteSwiped
                )
            }
        )
    }
}

@Composable
private fun FabContent(
    isScrollingUp: Boolean,
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        val isSmallFab = isScrollingUp.not()

        AnimatedVisibility(
            visible = isSmallFab,
            content = {
                SmallAddFab(
                    onClick = onClick
                )
            }
        )

        AnimatedVisibility(
            visible = !isSmallFab,
            content = {
                ExtendedAddFab(
                    title = title,
                    onClick = onClick
                )
            }
        )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExpensesListContent(
    expenses: List<ExpenseItem>,
    editLabel: String,
    paidByPrefix: String,
    onEditClicked: (Long) -> Unit,
    onDeleteSwiped: (Long) -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(expenses, key = { _, item -> item.id }) { index, item ->
            DismissItemContent(
                modifier = Modifier.animateItemPlacement(tween(500)),
                currentId = item.id,
                onDismiss = onDeleteSwiped,
                content = {
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

@Composable
private fun HeaderActionButtonsContent(
    isGrid: Boolean,
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
                painter = painterResource(
                    id = if (isGrid) {
                        R.drawable.ic_list_view
                    } else {
                        R.drawable.ic_grid_view
                    }
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }

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
                                color = MaterialTheme.colorScheme.tertiary,
                                fontWeight = FontWeight.Normal
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
                    tint = MaterialTheme.colorScheme.tertiary
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

@Composable
private fun ExpensesEmptyContent(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExpensesGridContent(
    expenses: List<ExpenseItem>,
    scrollState: LazyStaggeredGridState,
    onEditClicked: (Long) -> Unit,
    onDeleteSwiped: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        state = scrollState,
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(expenses, key = { _, item -> item.id }) { index, item ->
            val isFromEndToStart by remember(scrollState.layoutInfo.visibleItemsInfo) {
                mutableStateOf(
                    scrollState.layoutInfo
                        .visibleItemsInfo
                        .find { it.index == index }
                        ?.let { it.lane % 2 == 0 }
                        ?: false
                )
            }

            DismissItemContent(
                modifier = Modifier.animateItemPlacement(tween(500)),
                currentId = item.id,
                onDismiss =  onDeleteSwiped,
                isFromEndToStart = isFromEndToStart,
                content = {
                    ExpenseItemContent(
                        modifier = Modifier.fillMaxWidth(),
                        item = item,
                        editLabel = "",
                        paidByPrefix = "",
                        onEditClicked = onEditClicked
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissItemContent(
    currentId: Long,
    onDismiss: (Long) -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    isFromEndToStart: Boolean = true
) {
    val state = rememberDismissState()

    val dismissDirection = if (isFromEndToStart) {
        DismissDirection.EndToStart
    } else {
        DismissDirection.StartToEnd
    }

    if (state.isDismissed(dismissDirection)) {
        onDismiss(currentId)

        LaunchedEffect(currentId) {
            state.snapTo(DismissValue.Default)
        }
    }

    if (state.dismissDirection == dismissDirection) {
        val haptic = LocalHapticFeedback.current
        LaunchedEffect(Unit) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    SwipeToDismiss(
        state = state,
        modifier = modifier,
        directions = setOf(dismissDirection),
        background = {
            val dismissValue = if (isFromEndToStart) {
                DismissValue.DismissedToStart
            } else {
                DismissValue.DismissedToEnd
            }

            val backgroundColor by animateColorAsState(
                targetValue = when (state.targetValue) {
                    dismissValue -> Color.Red.copy(alpha = 0.8f)
                    else -> Color.White
                },
                label = ""
            )

            val iconScale by animateFloatAsState(
                targetValue = if (state.targetValue == dismissValue) {
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
                    .padding(all = 16.dp),
                contentAlignment = if (isFromEndToStart) {
                    Alignment.CenterEnd
                } else {
                    Alignment.CenterStart
                }
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
            content.invoke(this)
        }
    )
}
