package com.dzhafarov.moneykeeper.home.ui.content.general

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun DescriptionSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = description,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onBackground
        )
    )
}
