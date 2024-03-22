package com.dzhafarov.about_app.ui.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.dzhafarov.about_app.presentation.AboutAppUiAction
import com.dzhafarov.about_app.presentation.AboutAppUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun ScreenContent(
    state: StateFlow<AboutAppUiState>,
    onAction: (AboutAppUiAction) -> Unit
) {
    val uiState by state.collectAsState()

    AlertDialog(
        onDismissRequest = { onAction(AboutAppUiAction.OnDismiss) },
        icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null
            )
        },
        iconContentColor = MaterialTheme.colorScheme.primary,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.title,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.text,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Button(onClick = { onAction(AboutAppUiAction.OnDismiss) }) {
                Text(text = uiState.confirm)
            }
        }
    )
}
