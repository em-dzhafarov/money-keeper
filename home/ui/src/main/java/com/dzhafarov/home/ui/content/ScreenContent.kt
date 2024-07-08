package com.dzhafarov.home.ui.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dzhafarov.core.navigation.Destination
import com.dzhafarov.core.navigation.navigateTo
import com.dzhafarov.home.presentation.HomeUiAction
import com.dzhafarov.home.presentation.HomeUiState
import com.dzhafarov.home.ui.R
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun ScreenContent(
    state: StateFlow<HomeUiState>,
    snackbarHostState: SnackbarHostState,
    navController: NavController,
    onAction: (HomeUiAction) -> Unit
) {
    val uiState: HomeUiState by state.collectAsState()
    val lazyListState = rememberLazyListState()
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val isGrid by remember(uiState.displayMode) { mutableStateOf(uiState.displayMode.isGrid()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            HomeBottomAppBar(navController, isGrid, onAction)
        },
        topBar = {
            ToolbarContent(
                title = uiState.title,
                onAction = onAction
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            RenderAnimatedContent(uiState.expenses.isEmpty()) {
                EmptyContent(
                    message = uiState.emptyExpensesMessage
                )
            }

            RenderAnimatedContent(isGrid) {
                GridContent(
                    expenses = uiState.expenses,
                    scrollState = lazyStaggeredGridState,
                    onAction = onAction
                )
            }

            RenderAnimatedContent(isGrid.not()) {
                ListContent(
                    scrollState = lazyListState,
                    expenses = uiState.expenses,
                    onAction = onAction
                )
            }
        }
    }
}

@Composable
private fun RenderAnimatedContent(
    isVisible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isVisible,
        enter = scaleIn() + fadeIn(),
        exit = scaleOut() + fadeOut(),
        content = content
    )
}

@Composable
private fun HomeBottomAppBar(navController: NavController, isGrid: Boolean, onAction: (HomeUiAction) -> Unit) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { onAction(HomeUiAction.OnViewLookingClick) }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(
                        id = if (isGrid) R.drawable.ic_list_view else R.drawable.ic_grid_view
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(onClick = { onAction(HomeUiAction.OnFilterClick) } ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.filter),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(onClick = { onAction(HomeUiAction.OnSearchClick) } ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigateTo(Destination.Screen.Expense, listOf(0L)) },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.home_screen_add_expense)
                )
            }
        }
    )
}
