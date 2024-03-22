package com.dzhafarov.moneykeeper.home.ui.content.general

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dzhafarov.moneykeeper.expense.presentation.CurrencyItem

@Composable
internal fun AmountSection(
    amount: String,
    currency: CurrencyItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = currency.iconRes),
            contentDescription = currency.code,
            tint = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = amount,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Medium
            )
        )
    }
}