@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.home.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dzhafarov.core.ui.BaseTopBar
import com.dzhafarov.home.presentation.HomeUiAction
import com.dzhafarov.home.ui.R

@Composable
internal fun ToolbarContent(
    title: String,
    isGrid: Boolean,
    isFilterEmpty: Boolean,
    onAction: (HomeUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseTopBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = Icons.Default.Home,
        onNavigationIconPressed = { onAction(HomeUiAction.OnHomeClick) },
        actions = {
            ToolbarActionButtons(
                isGrid = isGrid,
                isFilterEmpty = isFilterEmpty,
                onAction = onAction
            )
        }
    )
}

@Composable
private fun ToolbarActionButtons(
    isGrid: Boolean,
    isFilterEmpty: Boolean,
    onAction: (HomeUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        ViewLookingActionButton(isGrid) { onAction(HomeUiAction.OnViewLookingClick) }
        FilterActionButton(isFilterEmpty) { onAction(HomeUiAction.OnFilterClick) }
        SearchActionButton { onAction(HomeUiAction.OnSearchClick) }
    }
}

@Composable
private fun ViewLookingActionButton(isGrid: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
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
}

@Composable
private fun FilterActionButton(isEmpty: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = null,
            tint = if (isEmpty) {
                MaterialTheme.colorScheme.secondary
            } else {
                MaterialTheme.colorScheme.primary
            }
        )
    }
}

@Composable
private fun SearchActionButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
