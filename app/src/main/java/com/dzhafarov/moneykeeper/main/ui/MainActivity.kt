package com.dzhafarov.moneykeeper.main.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dzhafarov.moneykeeper.main.presentation.MainViewModel
import com.dzhafarov.moneykeeper.main.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.state.value.isSplashShown
        }

        setContent {
            val state by viewModel.state.collectAsState()

            AppTheme(
                darkTheme = state.isDarkTheme,
                dynamicColor = state.isDynamicTheme,
                content = { NavigationGraph() }
            )
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        viewModel.invalidateThemeIfNeeded(this)
    }
}
