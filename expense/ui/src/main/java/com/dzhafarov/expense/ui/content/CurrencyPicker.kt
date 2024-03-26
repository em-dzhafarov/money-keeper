package com.dzhafarov.expense.ui.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.dzhafarov.expense.presentation.CurrencyItem

@Composable
internal fun CurrencyPicker(
    onDismiss: () -> Unit,
    onSelect: (CurrencyItem) -> Unit,
    items: List<CurrencyItem>,
    selected: CurrencyItem?,
    modifier: Modifier = Modifier
) {
    Popup(
        alignment = Alignment.CenterEnd,
        onDismissRequest = onDismiss,
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items) { currency ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.2f)
                        .clickable { onSelect(currency) }
                        .padding(8.dp),
                    text = currency.code,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (selected == currency) {
                            MaterialTheme.colorScheme.onSecondary
                        } else {
                            MaterialTheme.colorScheme.inversePrimary
                        }
                    )
                )
            }
        }
    }
}