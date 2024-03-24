package com.dzhafarov.filters.ui.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.filters.presentation.states.DescriptionUiState

@Composable
internal fun DescriptionSection(
    state: DescriptionUiState,
    onClick: () -> Unit,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                value = state.value,
                onValueChange = onValueChanged,
                label = { Text(text = state.hint) }
            )
        }
    )
}
