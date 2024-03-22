@file:OptIn(ExperimentalFoundationApi::class)

package com.dzhafarov.moneykeeper.home.ui.content

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.moneykeeper.expense.presentation.ExpenseItem
import com.dzhafarov.moneykeeper.home.presentation.HomeUiAction
import com.dzhafarov.moneykeeper.home.ui.content.general.AmountSection
import com.dzhafarov.moneykeeper.home.ui.content.general.DateTimeSection
import com.dzhafarov.moneykeeper.home.ui.content.general.DescriptionSection
import com.dzhafarov.moneykeeper.home.ui.content.general.DismissItemSection
import com.dzhafarov.moneykeeper.home.ui.content.general.PaymentMethodSection
import com.dzhafarov.moneykeeper.home.ui.content.general.PaymentReasonIcon
import com.dzhafarov.moneykeeper.home.ui.content.general.PaymentReasonSection

@Composable
internal fun ListContent(
    expenses: List<ExpenseItem>,
    scrollState: LazyListState,
    onAction: (HomeUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(expenses, key = { _, item -> item.id }) { index, item ->
            DismissItemSection(
                modifier = Modifier.animateItemPlacement(tween(500)),
                currentId = item.id,
                onDismiss = { onAction(HomeUiAction.OnExpenseDeleteSwipe(it)) },
                content = {
                    HomeListItem(
                        modifier = Modifier.fillMaxWidth(),
                        item = item,
                        onClick = { onAction(HomeUiAction.OnExpenseEditClick(it)) }
                    )
                }
            )

            if (index != expenses.lastIndex) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun HomeListItem(
    item: ExpenseItem,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(item.id) }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PaymentReasonIcon(reason = item.reason)

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    PaymentReasonSection(reason = item.reason)
                    AmountSection(amount = item.amount, currency = item.currency)
                }

                Spacer(modifier = Modifier.weight(1f))

                Column(horizontalAlignment = Alignment.End) {
                    PaymentMethodSection(method = item.method)
                    Spacer(modifier = Modifier.height(8.dp))
                    DateTimeSection(dateTime = item.time)
                }
            }

            if (item.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                DescriptionSection(description = item.description)
            }
        }
    }
}
