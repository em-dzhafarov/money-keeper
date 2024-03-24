package com.dzhafarov.filters.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.filters.presentation.states.PriceRangeUiState

@Composable
internal fun PriceRangeSection(
    state: PriceRangeUiState,
    onClick: () -> Unit,
    onPriceRangeChanged: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                val minAmount = (state.minAmount ?: state.initialMinAmount)
                val maxAmount = (state.maxAmount ?: state.initialMaxAmount)

                RangeSlider(
                    value = minAmount.toFloat()..maxAmount.toFloat(),
                    enabled = state.initialMinAmount != state.initialMaxAmount,
                    valueRange = state.initialMinAmount.toFloat()..state.initialMaxAmount.toFloat(),
                    onValueChange = onPriceRangeChanged
                )

                Row {
                    Text(
                        text = minAmount.toInt().toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = maxAmount.toInt().toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    )
}