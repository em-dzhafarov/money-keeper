package com.dzhafarov.moneykeeper.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationIconPressed: () -> Unit = {},
    navigationIcon: ImageVector = Icons.Default.ArrowBack,
    navigationIconContentDescription: String? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Surface(shadowElevation = 4.dp, modifier = modifier) {
        TopAppBar(
            title = { Text(text = title) },
            actions = actions,
            navigationIcon = {
                IconButton(onClick = onNavigationIconPressed) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription
                    )
                }
            }
        )
    }
}
