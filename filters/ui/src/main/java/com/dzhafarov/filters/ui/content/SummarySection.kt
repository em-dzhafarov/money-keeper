package com.dzhafarov.filters.ui.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dzhafarov.filters.presentation.states.SummaryUiState
import com.dzhafarov.filters.ui.R

@Composable
internal fun SummarySection(
    state: SummaryUiState,
    onApplyClick: () -> Unit,
    onCancelClick: () -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        if (state.isApplied) {
            OutlinedButton(onClick = onClearClick) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_clear),
                    contentDescription = state.clear
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = state.clear)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onCancelClick,
            border = BorderStroke(0.dp, Color.Transparent)
        ) {
            Text(text = state.cancel)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(onClick = onApplyClick) {
            Text(text = state.apply)
        }
    }
}
