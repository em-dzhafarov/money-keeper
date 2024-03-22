@file:OptIn(ExperimentalFoundationApi::class)

package com.dzhafarov.moneykeeper.home.ui.content.general

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dzhafarov.moneykeeper.expense.presentation.PaymentReasonItem

@Composable
internal fun PaymentReasonSection(
    reason: PaymentReasonItem,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.basicMarquee(),
        text = reason.title,
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        ),
        maxLines = 1
    )
}

@Composable
internal fun PaymentReasonIcon(
    reason: PaymentReasonItem,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier.size(40.dp),
        painter = painterResource(id = reason.resourceId),
        contentDescription = reason.title,
        tint = MaterialTheme.colorScheme.primary
    )
}
