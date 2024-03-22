package com.dzhafarov.date_time.ui.general

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ColumnScope.DateTimeButtons(
    cancel: String,
    confirm: String,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    Row(modifier = Modifier.align(Alignment.End)) {
        CancelButton(cancel, onCancel)
        Spacer(modifier = Modifier.width(8.dp))
        ConfirmButton(confirm, onConfirm)
    }
}

@Composable
private fun CancelButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        content = { Text(text = text) }
    )
}

@Composable
private fun ConfirmButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        content = { Text(text = text) }
    )
}
