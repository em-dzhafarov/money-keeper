package com.dzhafarov.moneykeeper.expense.ui.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AddExpenseStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AddExpenseStringProvider {

    override suspend fun title(): String {
        return context.getString(R.string.add_expense_screen)
    }
}