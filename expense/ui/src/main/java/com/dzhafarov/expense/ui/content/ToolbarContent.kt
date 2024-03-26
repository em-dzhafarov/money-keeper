@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.expense.ui.content

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dzhafarov.core.ui.BaseTopBar

@Composable
internal fun ToolbarContent(
    title: String,
    isDeleteVisible: Boolean,
    deleteLabel: String,
    onBackPressed: () -> Unit,
    onDeleteClick: () -> Unit
) {
    BaseTopBar(
        title = { Text(text = title) },
        navigationIcon = Icons.Default.ArrowBack,
        onNavigationIconPressed = onBackPressed,
        actions = {
            if (isDeleteVisible) {
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = deleteLabel
                    )
                }
            }
        }
    )
}