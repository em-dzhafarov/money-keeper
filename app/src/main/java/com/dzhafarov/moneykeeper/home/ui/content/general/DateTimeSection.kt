package com.dzhafarov.moneykeeper.home.ui.content.general

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun DateTimeSection(
    dateTime: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = dateTime,
        style = MaterialTheme.typography.bodySmall
    )
}
