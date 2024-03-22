package com.dzhafarov.moneykeeper.date_time.ui.general

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun DateTimeTitle(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}
