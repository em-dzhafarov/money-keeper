package com.dzhafarov.moneykeeper.expense.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.moneykeeper.core.ui.BaseTopBar
import com.dzhafarov.moneykeeper.core.ui.Destination
import com.dzhafarov.moneykeeper.core.utils.collectAsEffect
import com.dzhafarov.moneykeeper.core.utils.navigateTo
import com.dzhafarov.moneykeeper.date_time.ui.DateSelector
import com.dzhafarov.moneykeeper.date_time.ui.TimeSelector
import com.dzhafarov.moneykeeper.expense.presentation.AddExpenseAction
import com.dzhafarov.moneykeeper.expense.presentation.AddExpenseUiState
import com.dzhafarov.moneykeeper.expense.presentation.AddExpenseViewModel
import com.dzhafarov.moneykeeper.expense.presentation.CurrencyItem
import com.dzhafarov.moneykeeper.expense.presentation.PaymentMethodItem
import com.dzhafarov.moneykeeper.expense.presentation.PaymentReasonItem
import kotlinx.coroutines.flow.Flow

object AddExpenseScreen {
    const val SELECTED_EXPENSE_ID_ARG = "selected_expense_id"
}

@Composable
fun AddExpenseScreen(
    navController: NavController,
    expenseId: Long = 0,
    viewModel: AddExpenseViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = expenseId) {
        viewModel.initializeExpenseIfNeeded(expenseId)
    }

    val uiState by viewModel.uiState.collectAsState()

    AddExpenseActions(
        actions = viewModel.uiAction,
        navController = navController
    )

    ObserveResults(
        navController = navController,
        defaultDate = viewModel.initialDate,
        defaultTime = viewModel.initialTime,
        onDateResultReceived = viewModel::onDateSelected,
        onTimeResultReceived = viewModel::onTimeSelected
    )

    AddExpenseUiContent(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onBackPressed = viewModel::onBackPressed,
        onPaymentReasonSelected = viewModel::onPaymentReasonSelected,
        onPaymentMethodSelected = viewModel::onPaymentMethodSelected,
        onAmountChanged = viewModel::onAmountChanged,
        onCurrencySelected = viewModel::onCurrencySelected,
        onSaveClick = viewModel::onSaveClick,
        onDeleteClick = viewModel::onDeleteClick,
        onSelectDate = viewModel::onSelectDate,
        onSelectTime = viewModel::onSelectTime,
        onDescriptionChanged = viewModel::onDescriptionChanged
    )
}

@Composable
private fun ObserveResults(
    navController: NavController,
    defaultDate: Long,
    defaultTime: Pair<Int, Int>,
    onDateResultReceived: (Long) -> Unit,
    onTimeResultReceived: (Pair<Int, Int>) -> Unit
) {
    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.takeIf { it.contains(DateSelector.SELECTED_DATE_RESULT) }
        ?.getStateFlow(DateSelector.SELECTED_DATE_RESULT, defaultDate)
        ?.collectAsState()
        ?.value
        ?.let(onDateResultReceived)

    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.takeIf { it.contains(TimeSelector.SELECTED_TIME_RESULT) }
        ?.getStateFlow(TimeSelector.SELECTED_TIME_RESULT, defaultTime)
        ?.collectAsState()
        ?.value
        ?.let(onTimeResultReceived)
}

@Composable
private fun AddExpenseActions(
    actions: Flow<AddExpenseAction>,
    navController: NavController
) {
    actions.collectAsEffect { action ->
        when (action) {
            is AddExpenseAction.NavigateBack -> {
                navController.popBackStack()
            }

            is AddExpenseAction.SelectDate -> {
                navController.navigateTo(
                    destination = Destination.Dialog.DateSelector,
                    args = listOf(action.millis)
                )
            }

            is AddExpenseAction.SelectTime -> {
                navController.navigateTo(
                    destination = Destination.Dialog.TimeSelector,
                    args = listOf(action.hour, action.minute)
                )
            }

            is AddExpenseAction.ExpenseSaved -> {
                navController.popBackStack()
            }

            is AddExpenseAction.ExpenseUpdated -> {
                navController.popBackStack()
            }

            is AddExpenseAction.ExpenseDeleted -> {
                navController.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddExpenseUiContent(
    uiState: AddExpenseUiState,
    onBackPressed: () -> Unit,
    onPaymentReasonSelected: (PaymentReasonItem) -> Unit,
    onPaymentMethodSelected: (PaymentMethodItem) -> Unit,
    onAmountChanged: (String) -> Unit,
    onCurrencySelected: (CurrencyItem) -> Unit,
    onSelectDate: () -> Unit,
    onSelectTime: () -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BaseTopBar(
                title = { Text(text = uiState.title) },
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationIconPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        MainContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            paymentReasonTitle = uiState.paymentReasonTitle,
            paymentReasonError = uiState.paymentReasonError,
            paymentReasons = uiState.paymentReasons,
            selectedReason = uiState.selectedPaymentReason,
            onPaymentReasonSelected = onPaymentReasonSelected,
            paymentMethodTitle = uiState.paymentMethodTitle,
            paymentMethodError = uiState.paymentMethodError,
            paymentMethods = uiState.paymentMethods,
            selectedMethod = uiState.selectedPaymentMethod,
            onPaymentMethodSelected = onPaymentMethodSelected,
            amountError = uiState.amountError,
            amountLabel = uiState.amountLabel,
            amountValue = uiState.amountValue,
            amountTitle = uiState.amountTitle,
            onAmountValueChanged = onAmountChanged,
            saveTitle = uiState.saveTitle,
            onSaveClick = onSaveClick,
            isDeleteVisible = uiState.isDeleteVisible,
            deleteTitle = uiState.deleteTitle,
            onDeleteClick = onDeleteClick,
            currencies = uiState.currencies,
            selectedCurrency = uiState.selectedCurrency,
            onCurrencySelected = onCurrencySelected,
            dateTimeTitle = uiState.dateTimeTitle,
            dateTimeSeparator = uiState.dateTimeSeparator,
            formattedDate = uiState.formattedDate,
            formattedTime = uiState.formattedTime,
            onSelectDate = onSelectDate,
            onSelectTime = onSelectTime,
            descriptionTitle = uiState.descriptionTitle,
            descriptionValue = uiState.descriptionValue,
            descriptionLabel = uiState.descriptionLabel,
            onDescriptionChanged = onDescriptionChanged
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    paymentReasonTitle: String,
    paymentReasonError: String,
    paymentReasons: List<PaymentReasonItem>,
    selectedReason: PaymentReasonItem?,
    onPaymentReasonSelected: (PaymentReasonItem) -> Unit,
    paymentMethodTitle: String,
    paymentMethodError: String,
    paymentMethods: List<PaymentMethodItem>,
    selectedMethod: PaymentMethodItem?,
    onPaymentMethodSelected: (PaymentMethodItem) -> Unit,
    amountTitle: String,
    amountLabel: String,
    amountError: String,
    amountValue: String,
    onAmountValueChanged: (String) -> Unit,
    currencies: List<CurrencyItem>,
    selectedCurrency: CurrencyItem?,
    onCurrencySelected: (CurrencyItem) -> Unit,
    dateTimeTitle: String,
    dateTimeSeparator: String,
    formattedDate: String,
    formattedTime: String,
    onSelectDate: () -> Unit,
    onSelectTime: () -> Unit,
    descriptionTitle: String,
    descriptionValue: String,
    descriptionLabel: String,
    onDescriptionChanged: (String) -> Unit,
    saveTitle: String,
    onSaveClick: () -> Unit,
    deleteTitle: String,
    isDeleteVisible: Boolean,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        PaymentReasonContent(
            title = paymentReasonTitle,
            error = paymentReasonError,
            items = paymentReasons,
            selected = selectedReason,
            onSelected = onPaymentReasonSelected
        )

        Spacer(Modifier.height(16.dp))

        PaymentMethodContent(
            title = paymentMethodTitle,
            error = paymentMethodError,
            items = paymentMethods,
            selected = selectedMethod,
            onSelected = onPaymentMethodSelected
        )

        Spacer(Modifier.height(16.dp))

        AmountContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = amountTitle,
            label = amountLabel,
            value = amountValue,
            errorText = amountError,
            onValueChanged = onAmountValueChanged,
            currency = selectedCurrency,
            currencies = currencies,
            onSelectCurrency = onCurrencySelected
        )

        Spacer(Modifier.height(16.dp))

        DateTimeContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            title = dateTimeTitle,
            separator = dateTimeSeparator,
            formattedDate = formattedDate,
            formattedTime = formattedTime,
            onSelectDate = onSelectDate,
            onSelectTime = onSelectTime
        )

        Spacer(modifier = Modifier.width(16.dp))

        DescriptionContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = descriptionTitle,
            label = descriptionLabel,
            value = descriptionValue,
            onValueChanged = onDescriptionChanged
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomButtonsContent(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .padding(16.dp),
            saveTitle = saveTitle,
            onSaveClick = onSaveClick,
            deleteTitle = deleteTitle,
            isDeleteVisible = isDeleteVisible,
            onDeleteClick = onDeleteClick
        )
    }
}

@Composable
private fun PaymentReasonContent(
    title: String,
    error: String,
    items: List<PaymentReasonItem>,
    selected: PaymentReasonItem?,
    onSelected: (PaymentReasonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            itemsIndexed(items) { index, item ->
                PaymentReasonItemContent(item, selected == item, onSelected)

                if (index != items.lastIndex) {
                    Spacer(Modifier.width(8.dp))
                }
            }
        }

        AnimatedVisibility(
            visible = error.isNotEmpty(),
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = error,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
    }
}

@Composable
private fun PaymentMethodContent(
    title: String,
    error: String,
    items: List<PaymentMethodItem>,
    selected: PaymentMethodItem?,
    onSelected: (PaymentMethodItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            itemsIndexed(items) { index, item ->
                PaymentMethodItemContent(item, selected == item, onSelected)

                if (index != items.lastIndex) {
                    Spacer(Modifier.width(8.dp))
                }
            }
        }

        AnimatedVisibility(
            visible = error.isNotEmpty(),
            enter = slideInVertically(),
            exit = slideOutVertically(),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = error,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
    }
}

@Composable
private fun PaymentReasonItemContent(
    item: PaymentReasonItem,
    isSelected: Boolean,
    onSelected: (PaymentReasonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .let {
                if (isSelected) {
                    it.border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(6.dp)
                    )
                } else {
                    it
                }
            }
            .clickable { onSelected(item) }
            .defaultMinSize(80.dp, 70.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = item.resourceId),
            contentDescription = item.title,
            tint = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onBackground
            }
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun PaymentMethodItemContent(
    item: PaymentMethodItem,
    isSelected: Boolean,
    onSelected: (PaymentMethodItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .let {
                if (isSelected) {
                    it.border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(6.dp)
                    )
                } else {
                    it
                }
            }
            .clickable { onSelected(item) }
            .defaultMinSize(80.dp, 70.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = item.resourceId),
            contentDescription = item.title,
            tint = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onBackground
            }
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun AmountContent(
    title: String,
    label: String,
    value: String,
    errorText: String,
    currencies: List<CurrencyItem>,
    currency: CurrencyItem?,
    onValueChanged: (String) -> Unit,
    onSelectCurrency: (CurrencyItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(8.dp))

        var isCurrencyPickerShown by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            isError = errorText.isNotEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium,
            trailingIcon = {
                Text(
                    modifier = Modifier.clickable { isCurrencyPickerShown = true },
                    text = currency?.code.orEmpty(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        )

        AnimatedVisibility(
            visible = errorText.isNotEmpty(),
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = errorText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }

        if (isCurrencyPickerShown) {
            CurrencyPicker(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(4.dp)
                ),
                items = currencies,
                onDismiss = { isCurrencyPickerShown = false },
                selected = currency,
                onSelect = { selected ->
                    onSelectCurrency(selected)
                    isCurrencyPickerShown = false
                }
            )
        }
    }
}

@Composable
private fun CurrencyPicker(
    onDismiss: () -> Unit,
    onSelect: (CurrencyItem) -> Unit,
    items: List<CurrencyItem>,
    selected: CurrencyItem?,
    modifier: Modifier = Modifier
) {
    Popup(
        alignment = Alignment.CenterEnd,
        onDismissRequest = onDismiss,
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items) { currency ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.2f)
                        .clickable { onSelect(currency) }
                        .padding(8.dp),
                    text = currency.code,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (selected?.value == currency.value) {
                            MaterialTheme.colorScheme.onSecondary
                        } else {
                            MaterialTheme.colorScheme.inversePrimary
                        }
                    )
                )
            }
        }
    }
}

@Composable
private fun BottomButtonsContent(
    saveTitle: String,
    onSaveClick: () -> Unit,
    isDeleteVisible: Boolean,
    deleteTitle: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSaveClick,
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = saveTitle,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        if (isDeleteVisible) {
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onDeleteClick,
                contentPadding = PaddingValues(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                ),
            ) {
                Text(
                    text = deleteTitle,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun DateTimeContent(
    title: String,
    separator: String,
    formattedDate: String,
    formattedTime: String,
    onSelectDate: () -> Unit,
    onSelectTime: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable { onSelectDate() }
                    .padding(8.dp),
                text = formattedDate,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                )
            )

            Text(
                modifier = Modifier.padding(4.dp),
                text = separator,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable { onSelectTime() }
                    .padding(8.dp),
                text = formattedTime,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}

@Composable
private fun DescriptionContent(
    title: String,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            minLines = 2,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            singleLine = false,
            maxLines = 2,
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
}
