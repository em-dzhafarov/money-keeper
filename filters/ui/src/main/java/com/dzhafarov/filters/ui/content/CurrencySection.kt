package com.dzhafarov.filters.ui.content

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dzhafarov.expense.presentation.CurrencyItem
import com.dzhafarov.filters.presentation.states.CurrencyUiState

@Composable
internal fun CurrencySection(
    state: CurrencyUiState,
    onClick: () -> Unit,
    onCurrencySelected: (CurrencyItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                state.currencies.forEach { item ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = if (item.code in state.selected.map { it.code }) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.Transparent
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onCurrencySelected(item) }
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "${item.name} (${item.code})",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.code,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    )
}
