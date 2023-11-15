package com.dzhafarov.moneykeeper.date_time.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.core.utils.collectAsEffect
import com.dzhafarov.moneykeeper.date_time.presentation.DateSelectorEvent
import com.dzhafarov.moneykeeper.date_time.presentation.DateSelectorUiState
import com.dzhafarov.moneykeeper.date_time.presentation.DateSelectorViewModel
import kotlinx.coroutines.flow.Flow

object DateSelector {
    const val SELECTED_DATE_RESULT = "selected_date_result"
    const val SELECTED_DATE_ARG = "selected_date_arg"
}

@Composable
fun DateSelector(
    navController: NavController,
    initial: Long?,
    viewModel: DateSelectorViewModel = hiltViewModel()
) {
    viewModel.initializeDefaults(initial)

    val uiState by viewModel.state.collectAsState()

    DateSelectorEvents(
        events = viewModel.events,
        navController = navController
    )

    DateSelectorContent(
        uiState = uiState,
        onDismiss = viewModel::onBackPressed,
        onSelected = viewModel::onDateSelected
    )
}

@Composable
private fun DateSelectorEvents(
    events: Flow<DateSelectorEvent>,
    navController: NavController
) {
    events.collectAsEffect { event ->
        when (event) {
            is DateSelectorEvent.NavigateBack -> {
                navController.popBackStack()
            }

            is DateSelectorEvent.Result -> {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(DateSelector.SELECTED_DATE_RESULT, event.value)

                navController.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateSelectorContent(
    uiState: DateSelectorUiState,
    onDismiss: () -> Unit,
    onSelected: (Long) -> Unit
) {
    val dateState = rememberDatePickerState(
        initialSelectedDateMillis = uiState.current
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onSelected(
                        dateState.selectedDateMillis ?: uiState.current
                    )
                },
                content = {
                    Text(text = uiState.confirm)
                }
            )
        },
        dismissButton = {
            OutlinedButton(
                onClick = {
                    onDismiss()
                },
                content = {
                    Text(text = uiState.cancel)
                }
            )
        }
    ) {
        DatePicker(
            state = dateState,
            showModeToggle = false,
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = uiState.title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
    }
}
