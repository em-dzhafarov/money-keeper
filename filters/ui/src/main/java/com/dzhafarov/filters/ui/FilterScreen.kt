package com.dzhafarov.filters.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dzhafarov.core.ui.utils.collectAsEffect
import com.dzhafarov.expense.presentation.CurrencyItem
import com.dzhafarov.expense.presentation.PaymentMethodItem
import com.dzhafarov.expense.presentation.PaymentReasonItem
import com.dzhafarov.filters.presentation.FilterViewModel
import com.dzhafarov.filters.presentation.states.CurrencyUiState
import com.dzhafarov.filters.presentation.states.DescriptionUiState
import com.dzhafarov.filters.presentation.states.PaymentMethodUiState
import com.dzhafarov.filters.presentation.states.PaymentReasonUiState
import com.dzhafarov.filters.presentation.states.PriceRangeUiState
import com.dzhafarov.filters.presentation.states.SummaryUiState

@Composable
fun FilterScreen(navController: NavController) {
    val viewModel: FilterViewModel = hiltViewModel()
    FilterScreenEvents(navController, viewModel)
    FilterScreenContent(viewModel)
}

@Composable
private fun FilterScreenEvents(
    navController: NavController,
    viewModel: FilterViewModel
) {
    viewModel.events.collectAsEffect { event ->
        when (event) {
            is com.dzhafarov.filters.presentation.FilterEvent.Dismiss -> {
                navController.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterScreenContent(
    viewModel: FilterViewModel
) = com.dzhafarov.core.ui.BaseBottomSheet(
    onDismiss = viewModel::onDismiss,
    content = { MainContent(viewModel) }
)

@Composable
private fun MainContent(viewModel: FilterViewModel) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PriceRangeSection(
            state = state.priceRange,
            onPriceRangeChanged = viewModel::onPriceRangeChanged,
            onClick = viewModel::onPriceRangeClicked
        )

        Spacer(modifier = Modifier.height(16.dp))

        CurrencySection(
            state = state.currency,
            onClick = viewModel::onCurrencyClicked,
            onCurrencySelected = viewModel::onCurrencySelected
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentMethodSection(
            state = state.method,
            onClick = viewModel::onPaymentMethodClicked,
            onMethodSelected = viewModel::onPaymentMethodSelected
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentReasonSection(
            state = state.reason,
            onClick = viewModel::onPaymentReasonClicked,
            onReasonSelected = viewModel::onPaymentReasonSelected
        )

        Spacer(modifier = Modifier.height(16.dp))

        DescriptionSection(
            state = state.description,
            onClick = viewModel::onDescriptionClicked,
            onValueChanged = viewModel::onDescriptionValueChanged
        )

        Spacer(modifier = Modifier.height(32.dp))

        SummarySection(
            state = state.summary,
            onApplyClick = viewModel::onApplyFiltersClicked,
            onCancelClick = viewModel::onCancelClicked,
            onClearClick = viewModel::onClearFiltersClicked
        )
    }
}

@Composable
private fun PriceRangeSection(
    state: PriceRangeUiState,
    onClick: () -> Unit,
    onPriceRangeChanged: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                val minAmount = (state.minAmount ?: state.initialMinAmount)
                val maxAmount = (state.maxAmount ?: state.initialMaxAmount)

                RangeSlider(
                    value = minAmount.toFloat()..maxAmount.toFloat(),
                    enabled = state.initialMinAmount != state.initialMaxAmount,
                    valueRange = state.initialMinAmount.toFloat()..state.initialMaxAmount.toFloat(),
                    onValueChange = onPriceRangeChanged
                )

                Row {
                    Text(
                        text = minAmount.toInt().toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = maxAmount.toInt().toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    )
}

@Composable
private fun DescriptionSection(
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

@Composable
private fun CurrencySection(
    state: CurrencyUiState,
    onClick: () -> Unit,
    onCurrencySelected: (CurrencyItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                state.currencies.forEach { item ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = if (item.code in state.selected.map { it.code }) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.Transparent
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onCurrencySelected(item) }
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "${item.name} (${item.code})",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.code,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun PaymentMethodSection(
    state: PaymentMethodUiState,
    onClick: () -> Unit,
    onMethodSelected: (PaymentMethodItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                state.methods.forEach { item ->
                    val isSelected = item.title in state.selected.map { it.title }

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.Transparent
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onMethodSelected(item) }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = item.resourceId),
                            contentDescription = item.title,
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onBackground
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun PaymentReasonSection(
    state: PaymentReasonUiState,
    onClick: () -> Unit,
    onReasonSelected: (PaymentReasonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SectionVisibilitySwitcher(
        modifier = modifier,
        title = state.title,
        onClick = onClick,
        isExpanded = state.isExpanded,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                state.reasons.forEach { item ->
                    val isSelected = item.title in state.selected.map { it.title }

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.Transparent
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onReasonSelected(item) }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = item.resourceId),
                            contentDescription = item.title,
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onBackground
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun SummarySection(
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

@Composable
private fun SectionVisibilitySwitcher(
    title: String,
    onClick: () -> Unit,
    isExpanded: Boolean,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick.invoke() }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = if (isExpanded) {
                    Icons.Filled.KeyboardArrowUp
                } else {
                    Icons.Filled.KeyboardArrowDown
                },
                contentDescription = null
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandIn(expandFrom = Alignment.CenterStart),
            exit = shrinkOut(shrinkTowards = Alignment.CenterStart),
            content = { content() }
        )
    }
}