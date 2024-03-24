package com.dzhafarov.filters.ui.content

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dzhafarov.expense.presentation.PaymentReasonItem
import com.dzhafarov.filters.presentation.states.PaymentReasonUiState

@Composable
internal fun PaymentReasonSection(
    state: PaymentReasonUiState,
    onClick: () -> Unit,
    onReasonSelected: (PaymentReasonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                state.reasons.forEach { item ->
                    val isSelected = item.title in state.selected.map { it.title }

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.Transparent
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onReasonSelected(item) }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = item.resourceId),
                            contentDescription = item.title,
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onBackground
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    )
}
