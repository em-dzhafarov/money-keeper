@file:OptIn(ExperimentalFoundationApi::class)

package com.dzhafarov.home.ui.content

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dzhafarov.expense.presentation.ExpenseItem
import com.dzhafarov.home.presentation.HomeUiAction
import com.dzhafarov.home.ui.content.general.AmountSection
import com.dzhafarov.home.ui.content.general.DateTimeSection
import com.dzhafarov.home.ui.content.general.DescriptionSection
import com.dzhafarov.home.ui.content.general.DismissItemSection
import com.dzhafarov.home.ui.content.general.PaymentMethodSection
import com.dzhafarov.home.ui.content.general.PaymentReasonSection
import com.dzhafarov.home.ui.content.general.PaymentReasonIcon

@Composable
internal fun GridContent(
    expenses: List<ExpenseItem>,
    scrollState: LazyStaggeredGridState,
    onAction: (com.dzhafarov.home.presentation.HomeUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        state = scrollState,
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(expenses, key = { _, item -> item.id }) { index, item ->
            val isFromEndToStart by remember(scrollState.layoutInfo.visibleItemsInfo) {
                mutableStateOf(
                    scrollState.layoutInfo
                        .visibleItemsInfo
                        .find { it.index == index }
                        ?.let { it.lane % 2 == 0 }
                        ?: false
                )
            }

            DismissItemSection(
                modifier = Modifier.animateItemPlacement(tween(500)),
                currentId = item.id,
                onDismiss = { onAction(HomeUiAction.OnExpenseDeleteSwipe(it)) },
                isFromEndToStart = isFromEndToStart,
                content = {
                    HomeGridItem(
                        modifier = Modifier.fillMaxWidth(),
                        item = item,
                        onClick = { onAction(HomeUiAction.OnExpenseEditClick(it)) }
                    )
                }
            )
        }
    }
}

@Composable
private fun HomeGridItem(
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
            }

            if (item.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                DescriptionSection(description = item.description)
            }

            Spacer(modifier = Modifier.height(8.dp))
            PaymentMethodSection(method = item.method)
            Spacer(modifier = Modifier.height(8.dp))
            DateTimeSection(dateTime = item.time)
        }
    }
}
