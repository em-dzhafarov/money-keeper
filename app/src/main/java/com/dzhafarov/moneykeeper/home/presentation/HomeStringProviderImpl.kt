package com.dzhafarov.moneykeeper.home.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : HomeStringProvider {

    override suspend fun title(): String {
        return context.getString(R.string.home_screen)
    }

    override suspend fun welcome(userName: String): String {
        return context.getString(R.string.home_screen_welcome_message).format(userName)
    }

    override suspend fun noExpensesYet(): String {
        return context.getString(R.string.home_screen_no_expenses_yet)
    }

    override suspend fun addExpense(): String {
        return context.getString(R.string.home_screen_add_expense)
    }

    override suspend fun editExpense(): String {
        return context.getString(R.string.home_screen_edit_expense)
    }

    override suspend fun paidByPrefix(): String {
        return context.getString(R.string.home_screen_paid_by_prefix)
    }

    override suspend fun expenseDeletedMessage(): String {
        return context.getString(R.string.home_screen_expense_deleted)
    }

    override suspend fun undoDeleteButton(): String {
        return context.getString(R.string.home_screen_expense_deleted_undo)
    }
}