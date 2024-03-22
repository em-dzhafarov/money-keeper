@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.moneykeeper.date_time.ui.date

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.moneykeeper.date_time.presentation.date.DateUiAction
import com.dzhafarov.moneykeeper.date_time.presentation.date.DateUiState
import com.dzhafarov.moneykeeper.date_time.ui.general.DateTimeButtons
import com.dzhafarov.moneykeeper.date_time.ui.general.DateTimeDialog
import com.dzhafarov.moneykeeper.date_time.ui.general.DateTimeTitle
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun DateSelectorContent(
    state: StateFlow<DateUiState>,
    onAction: (DateUiAction) -> Unit
) {
    val uiState by state.collectAsState()

    DateTimeDialog(onDismiss = { onAction(DateUiAction.OnDismiss) }) {
        val pickerState = rememberDatePickerState(
            initialSelectedDateMillis = uiState.current
        )

        DateTimeTitle(text = uiState.title)

        DatePicker(
            modifier = Modifier.padding(8.dp),
            state = pickerState,
            title = null,
            showModeToggle = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        DateTimeButtons(
            cancel = uiState.cancel,
            confirm = uiState.confirm,
            onCancel = { onAction(DateUiAction.OnDismiss) },
            onConfirm = {
                onAction(
                    DateUiAction.OnDateSelected(
                        millis = pickerState.selectedDateMillis ?: uiState.current
                    )
                )
            }
        )
    }
}
