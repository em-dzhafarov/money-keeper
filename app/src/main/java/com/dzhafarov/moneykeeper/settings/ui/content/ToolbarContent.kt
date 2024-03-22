@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.moneykeeper.settings.ui.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dzhafarov.core.ui.BaseTopBar

@Composable
internal fun ToolbarContent(
    title: String,
    onNavClick: () -> Unit
) {
    BaseTopBar(
        title = { Text(text = title) },
        onNavigationIconPressed = onNavClick
    )
}
