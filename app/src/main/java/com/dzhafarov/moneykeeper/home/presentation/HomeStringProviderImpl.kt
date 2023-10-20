package com.dzhafarov.moneykeeper.home.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : HomeStringProvider {

    override suspend fun welcome(userName: String): String {
        return context.getString(R.string.home_screen_welcome_message).format(userName)
    }

    override suspend fun noExpensesYet(): String {
        return context.getString(R.string.home_screen_no_expenses_yet)
    }
}