package com.dzhafarov.filters.ui.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun SectionVisibilitySwitcher(
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
