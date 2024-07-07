package com.dzhafarov.home.ui.content.general

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import com.dzhafarov.expense.presentation.CurrencyItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AmountSection(
    amount: String,
    currency: CurrencyItem,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.basicMarquee(),
        maxLines = 1,
        text = buildAnnotatedString {
            append(amount)

            addStyle(
                SpanStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                ),
                0,
                amount.length
            )

            append(" [${currency.code}]")

            addStyle(
                SpanStyle(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Medium
                ),
                amount.length,
                length
            )
        }
    )
}