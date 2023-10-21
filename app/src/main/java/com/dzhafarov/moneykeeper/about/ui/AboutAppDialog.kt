package com.dzhafarov.moneykeeper.about.ui

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.about.presentation.AboutAppUiState
import com.dzhafarov.moneykeeper.about.presentation.AboutAppViewModel

@Composable
fun AboutAppDialog(navController: NavController) {
    val viewModel = hiltViewModel<AboutAppViewModel>()
    val uiState: AboutAppUiState by viewModel.uiState.collectAsState()

    AlertDialog(
        onDismissRequest = { navController.popBackStack() },
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
            Button(onClick = { navController.popBackStack() }) {
                Text(text = uiState.confirm)
            }
        }
    )
}
