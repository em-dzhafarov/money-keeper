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
import com.dzhafarov.moneykeeper.date_time.presentation.DateSelectorUiAction
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

    val uiState by viewModel.uiState.collectAsState()

    DateSelectorActions(
        actions = viewModel.uiAction,
        navController = navController
    )

    DateSelectorUiContent(
        uiState = uiState,
        onDismiss = viewModel::onBackPressed,
        onSelected = viewModel::onDateSelected
    )
}

@Composable
private fun DateSelectorActions(
    actions: Flow<DateSelectorUiAction>,
    navController: NavController
) {
    actions.collectAsEffect { action ->
        when (action) {
            is DateSelectorUiAction.NavigateBack -> {
                navController.popBackStack()
            }

            is DateSelectorUiAction.Result -> {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(DateSelector.SELECTED_DATE_RESULT, action.value)

                navController.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateSelectorUiContent(
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
