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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dzhafarov.core.ui.utils.isScrollingUp
import com.dzhafarov.home.presentation.HomeUiAction
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun ScreenContent(
    state: StateFlow<com.dzhafarov.home.presentation.HomeUiState>,
    snackbarHostState: SnackbarHostState,
    onAction: (HomeUiAction) -> Unit
) {
    val uiState: com.dzhafarov.home.presentation.HomeUiState by state.collectAsState()
    val lazyListState = rememberLazyListState()
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val isGrid by remember(uiState.displayMode) { mutableStateOf(uiState.displayMode.isGrid()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FabContent(
                isScrollingUp = if (isGrid) {
                    lazyStaggeredGridState.isScrollingUp()
                } else {
                    lazyListState.isScrollingUp()
                },
                onClick = { onAction(HomeUiAction.OnAddExpenseClick) },
                title = uiState.addExpenseMessage
            )
        },
        topBar = {
            ToolbarContent(
                title = uiState.title,
                isGrid = isGrid,
                isFilterEmpty = uiState.isFilterEmpty,
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
