@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.home.ui.content.general

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

@Composable
internal fun DismissItemSection(
    currentId: Long,
    onDismiss: (Long) -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    isFromEndToStart: Boolean = true
) {
    val state = rememberDismissState()

    val dismissDirection = if (isFromEndToStart) {
        DismissDirection.EndToStart
    } else {
        DismissDirection.StartToEnd
    }

    if (state.isDismissed(dismissDirection)) {
        onDismiss(currentId)

        LaunchedEffect(currentId) {
            state.snapTo(DismissValue.Default)
        }
    }

    if (state.dismissDirection == dismissDirection) {
        val haptic = LocalHapticFeedback.current
        LaunchedEffect(Unit) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    SwipeToDismiss(
        state = state,
        modifier = modifier,
        directions = setOf(dismissDirection),
        background = { SwipeToDismissBackground(isFromEndToStart, state) },
        dismissContent = { content.invoke(this) }
    )
}

@Composable
private fun SwipeToDismissBackground(isFromEndToStart: Boolean, state: DismissState) {
    val dismissValue = if (isFromEndToStart) {
        DismissValue.DismissedToStart
    } else {
        DismissValue.DismissedToEnd
    }

    val backgroundColor by animateColorAsState(
        targetValue = when (state.targetValue) {
            dismissValue -> MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
            else -> MaterialTheme.colorScheme.background
        },
        label = "backgroundColorAnimation"
    )

    val iconScale by animateFloatAsState(
        targetValue = if (state.targetValue == dismissValue) 1.3f else 0.5f,
        label = "iconScaleAnimation"
    )

    Box(
        Modifier
            .clip(CardDefaults.shape)
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(all = 16.dp),
        contentAlignment = if (isFromEndToStart) {
            Alignment.CenterEnd
        } else {
            Alignment.CenterStart
        }
    ) {
        Icon(
            modifier = Modifier.scale(iconScale),
            imageVector = Icons.Outlined.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onError
        )
    }
}
