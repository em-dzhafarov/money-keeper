@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BaseTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onNavigationIconPressed: () -> Unit = {},
    navigationIcon: ImageVector? = Icons.Default.ArrowBack,
    navigationIconContentDescription: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrollingBehavior: TopAppBarScrollBehavior? = null,
    isLarge: Boolean = false
) {
    Surface(shadowElevation = 4.dp, modifier = modifier) {
        if (isLarge) {
            LargeTopAppBar(
                title = title,
                actions = actions,
                scrollBehavior = scrollingBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    if (navigationIcon != null) {
                        IconButton(onClick = onNavigationIconPressed) {
                            Icon(
                                imageVector = navigationIcon,
                                contentDescription = navigationIconContentDescription
                            )
                        }
                    }
                }
            )
        } else {
            TopAppBar(
                title = title,
                actions = actions,
                scrollBehavior = scrollingBehavior,
                navigationIcon = {
                    if (navigationIcon != null) {
                        IconButton(onClick = onNavigationIconPressed) {
                            Icon(
                                imageVector = navigationIcon,
                                contentDescription = navigationIconContentDescription
                            )
                        }
                    }
                }
            )
        }
    }
}
