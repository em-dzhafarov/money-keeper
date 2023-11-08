package com.dzhafarov.moneykeeper.date_time.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.core.utils.collectAsEffect
import com.dzhafarov.moneykeeper.date_time.presentation.TimeSelectorEvent
import com.dzhafarov.moneykeeper.date_time.presentation.TimeSelectorUiState
import com.dzhafarov.moneykeeper.date_time.presentation.TimeSelectorViewModel
import kotlinx.coroutines.flow.Flow

object TimeSelector {
    const val SELECTED_TIME_RESULT = "selected_time_result"
    const val SELECTED_HOUR_ARG = "selected_hour_arg"
    const val SELECTED_MINUTE_ARG = "selected_minute_arg"
}

@Composable
fun TimeSelector(
    navController: NavController,
    hours: Int?,
    minutes: Int?,
    viewModel: TimeSelectorViewModel = hiltViewModel()
) {
    LaunchedEffect(hours, minutes) {
        viewModel.initializeDefaults(hours, minutes)
    }
    
    val uiState by viewModel.state.collectAsState()

    TimeSelectorEvents(
        events = viewModel.events,
        navController = navController
    )

    TimeSelectorContent(
        uiState = uiState,
        onDismiss = viewModel::onBackPressed,
        onTimeSelected = viewModel::onTimeSelected
    )
}

@Composable
private fun TimeSelectorEvents(
    events: Flow<TimeSelectorEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is TimeSelectorEvent.NavigateBack -> {
                navController.popBackStack()
            }

            is TimeSelectorEvent.Result -> {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(TimeSelector.SELECTED_TIME_RESULT, event.value)

                navController.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimeSelectorContent(
    uiState: TimeSelectorUiState,
    onDismiss: () -> Unit,
    onTimeSelected: (Pair<Int, Int>) -> Unit
) {
    val state = rememberTimePickerState(
        initialHour = uiState.hour,
        initialMinute = uiState.minute
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.clip(DatePickerDefaults.shape),
            tonalElevation = DatePickerDefaults.TonalElevation,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = uiState.title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                TimePicker(
                    modifier = Modifier.padding(8.dp),
                    state = state,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.align(Alignment.End)) {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        content = { Text(text = uiState.cancel) }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { onTimeSelected(state.hour to state.minute) },
                        content = { Text(text = uiState.confirm) }
                    )
                }
            }
        }
    }
}
