@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.filters.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.core.ui.BaseBottomSheet
import com.dzhafarov.filters.presentation.FilterUiAction
import com.dzhafarov.filters.presentation.states.FilterUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun FilterContent(
    state: StateFlow<FilterUiState>,
    onAction: (FilterUiAction) -> Unit
) = BaseBottomSheet(
    onDismiss = { onAction(FilterUiAction.OnDismiss) },
    content = { MainContent(state, onAction) }
)

@Composable
private fun MainContent(
    state: StateFlow<FilterUiState>,
    onAction: (FilterUiAction) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val uiState by state.collectAsState()

        PriceRangeSection(
            state = uiState.priceRange,
            onPriceRangeChanged = { onAction(FilterUiAction.OnPriceRangeChanged(it)) },
            onClick = { onAction(FilterUiAction.OnPriceRangeClicked) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CurrencySection(
            state = uiState.currency,
            onClick = { onAction(FilterUiAction.OnCurrencyClicked) },
            onCurrencySelected = { onAction(FilterUiAction.OnCurrencySelected(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentMethodSection(
            state = uiState.method,
            onClick = { onAction(FilterUiAction.OnPaymentMethodClicked) },
            onMethodSelected = { onAction(FilterUiAction.OnPaymentMethodSelected(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentReasonSection(
            state = uiState.reason,
            onClick = { onAction(FilterUiAction.OnPaymentReasonClicked) },
            onReasonSelected = { onAction(FilterUiAction.OnPaymentReasonSelected(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DescriptionSection(
            state = uiState.description,
            onClick = { onAction(FilterUiAction.OnDescriptionClicked) },
            onValueChanged = { onAction(FilterUiAction.OnDescriptionValueChanged(it)) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        SummarySection(
            state = uiState.summary,
            onApplyClick = { onAction(FilterUiAction.OnApplyFiltersClicked) },
            onCancelClick = { onAction(FilterUiAction.OnCancelClicked) },
            onClearClick = { onAction(FilterUiAction.OnClearFiltersClicked) }
        )
    }
}

