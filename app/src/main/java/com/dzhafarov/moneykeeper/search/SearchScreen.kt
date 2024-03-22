@file:OptIn(ExperimentalMaterial3Api::class)

package com.dzhafarov.moneykeeper.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dzhafarov.core.ui.BaseBottomSheet

@Composable
fun SearchScreen(navController: NavController) {
    BaseBottomSheet(
        onDismiss = { navController.popBackStack() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Search",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
