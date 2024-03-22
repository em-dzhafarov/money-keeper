package com.dzhafarov.moneykeeper.home.ui.content.general

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dzhafarov.moneykeeper.expense.presentation.PaymentMethodItem

@Composable
internal fun PaymentMethodSection(
    method: PaymentMethodItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = method.title,
            style = MaterialTheme.typography.labelMedium
        )

        Icon(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(16.dp),
            painter = painterResource(id = method.resourceId),
            contentDescription = method.title
        )
    }
}
