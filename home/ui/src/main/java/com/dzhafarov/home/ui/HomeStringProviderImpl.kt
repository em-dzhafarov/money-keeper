package com.dzhafarov.home.ui

import android.content.Context
import com.dzhafarov.home.presentation.HomeStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class HomeStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : HomeStringProvider {

    override val title: String
        get() = context.getString(R.string.app_name)

    override val noExpensesYet: String
        get() = context.getString(R.string.home_screen_no_expenses_yet)

    override val noExpensesMatchTheGivenFilter: String
        get() = context.getString(R.string.home_screen_no_expenses_match_the_given_filter)

    override val noExpensesToFilterOut: String
        get() = context.getString(R.string.home_screen_no_expenses_to_filter_out)

    override val addExpense: String
        get() = context.getString(R.string.home_screen_add_expense)

    override val editExpense: String
        get() = context.getString(R.string.home_screen_edit_expense)

    override val paidByPrefix: String
        get() = context.getString(R.string.home_screen_paid_by_prefix)

    override val expenseDeletedMessage: String
        get() = context.getString(R.string.home_screen_expense_deleted)

    override val undoDeleteButton: String
        get() = context.getString(R.string.home_screen_expense_deleted_undo)
}