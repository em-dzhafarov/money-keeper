@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.home.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dzhafarov.core.ui.BaseTopBar
import com.dzhafarov.home.presentation.HomeUiAction
import com.dzhafarov.home.ui.R

@Composable
internal fun ToolbarContent(
    title: String,
    onAction: (HomeUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseTopBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = null,
        actions = { ToolbarActionButtons(onAction) }
    )
}

@Composable
private fun ToolbarActionButtons(
    onAction: (HomeUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        DashboardActionButton { onAction(HomeUiAction.OnDashboardClick) }
        SettingsActionButton { onAction(HomeUiAction.OnSettingsClick) }
    }
}

@Composable
private fun DashboardActionButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Filled.DateRange,
            contentDescription = stringResource(id = R.string.dashboard)
        )
    }
}

@Composable
private fun SettingsActionButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Filled.Settings,
            contentDescription = stringResource(id = R.string.settings)
        )
    }
}
