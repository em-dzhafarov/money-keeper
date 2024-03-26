package com.dzhafarov.expense.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.expense.presentation.ExpenseUiAction
import com.dzhafarov.expense.presentation.ExpenseUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun ExpenseContent(
    state: StateFlow<ExpenseUiState>,
    onAction: (ExpenseUiAction) -> Unit
) {
    val uiState by state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarContent(
                title = uiState.title,
                isDeleteVisible = uiState.isDeleteVisible,
                deleteLabel = uiState.deleteTitle,
                onBackPressed = { onAction(ExpenseUiAction.OnBackPressed) },
                onDeleteClick = { onAction(ExpenseUiAction.OnDeleteClick) }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            PaymentReasonSection(
                title = uiState.paymentReasonTitle,
                error = uiState.paymentReasonError,
                items = uiState.paymentReasons,
                selected = uiState.selectedPaymentReason,
                onSelected = { onAction(ExpenseUiAction.OnPaymentReasonSelected(it)) }
            )

            Spacer(Modifier.height(16.dp))

            PaymentMethodSection(
                title = uiState.paymentMethodTitle,
                error = uiState.paymentMethodError,
                items = uiState.paymentMethods,
                selected = uiState.selectedPaymentMethod,
                onSelected = { onAction(ExpenseUiAction.OnPaymentMethodSelected(it)) }
            )

            Spacer(Modifier.height(16.dp))

            AmountSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = uiState.amountTitle,
                label = uiState.amountLabel,
                value = uiState.amountValue,
                errorText = uiState.amountError,
                onValueChanged = { onAction(ExpenseUiAction.OnAmountChanged(it)) },
                currency = uiState.selectedCurrency,
                currencies = uiState.currencies,
                onSelectCurrency = { onAction(ExpenseUiAction.OnCurrencySelected(it)) }
            )

            Spacer(Modifier.height(16.dp))

            DateTimeSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                title = uiState.dateTimeTitle,
                separator = uiState.dateTimeSeparator,
                formattedDate = uiState.formattedDate,
                formattedTime = uiState.formattedTime,
                onSelectDate = { onAction(ExpenseUiAction.OnSelectDate) },
                onSelectTime = { onAction(ExpenseUiAction.OnSelectTime) }
            )

            Spacer(modifier = Modifier.width(16.dp))

            DescriptionSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = uiState.descriptionTitle,
                label = uiState.descriptionLabel,
                value = uiState.descriptionValue,
                onValueChanged = { onAction(ExpenseUiAction.OnDescriptionChanged(it)) }
            )

            Spacer(modifier = Modifier.weight(1f))

            BottomSection(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .padding(16.dp),
                saveTitle = uiState.saveTitle,
                onSaveClick = { onAction(ExpenseUiAction.OnSaveClick) }
            )
        }
    }
}
