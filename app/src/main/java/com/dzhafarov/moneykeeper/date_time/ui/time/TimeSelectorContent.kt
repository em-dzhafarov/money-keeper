@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.moneykeeper.date_time.ui.time

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.moneykeeper.date_time.presentation.time.TimeUiAction
import com.dzhafarov.moneykeeper.date_time.presentation.time.TimeUiState
import com.dzhafarov.moneykeeper.date_time.ui.general.DateTimeButtons
import com.dzhafarov.moneykeeper.date_time.ui.general.DateTimeDialog
import com.dzhafarov.moneykeeper.date_time.ui.general.DateTimeTitle
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun TimeSelectorContent(
    state: StateFlow<TimeUiState>,
    onAction: (TimeUiAction) -> Unit
) {
    val uiState by state.collectAsState()

    DateTimeDialog(onDismiss = { onAction(TimeUiAction.OnDismiss) }) {
        val pickerState = rememberTimePickerState(
            initialHour = uiState.hour,
            initialMinute = uiState.minute
        )

        DateTimeTitle(text = uiState.title)

        TimePicker(
            modifier = Modifier.padding(8.dp),
            state = pickerState,
        )

        Spacer(modifier = Modifier.height(16.dp))

        DateTimeButtons(
            cancel = uiState.cancel,
            confirm = uiState.confirm,
            onCancel = { onAction(TimeUiAction.OnDismiss) },
            onConfirm = {
                onAction(
                    TimeUiAction.OnTimeSelected(
                        hour = pickerState.hour,
                        minute = pickerState.minute
                    )
                )
            }
        )
    }
}
