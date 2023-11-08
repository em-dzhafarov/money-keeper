package com.dzhafarov.moneykeeper.expense.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExpenseStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExpenseStringProvider {

    override suspend fun title(isEdit: Boolean): String {
        return context.getString(
            if (isEdit) {
                R.string.edit_expense_screen
            } else {
                R.string.add_expense_screen
            }
        )
    }

    override suspend fun paymentReasonTitle(): String {
        return context.getString(R.string.expense_screen_payment_reason_title)
    }

    override suspend fun paymentReasonError(): String {
        return context.getString(R.string.expense_screen_reason_error)
    }

    override suspend fun paymentMethodTitle(): String {
        return context.getString(R.string.expense_screen_payment_method_title)
    }

    override suspend fun paymentMethodError(): String {
        return context.getString(R.string.expense_screen_method_error)
    }

    override suspend fun amountError(): String {
        return context.getString(R.string.expense_screen_amount_error)
    }

    override suspend fun amountLabel(): String {
        return context.getString(R.string.expense_screen_amount_label)
    }

    override suspend fun amountTitle(): String {
        return context.getString(R.string.expense_screen_amount_title)
    }

    override suspend fun saveTitle(isEdit: Boolean): String {
        return context.getString(
            if (isEdit) {
                R.string.expense_screen_update
            } else {
                R.string.expense_screen_save
            }
        )
    }

    override suspend fun deleteTitle(): String {
        return context.getString(R.string.expense_screen_delete)
    }

    override suspend fun dateTimeTitle(): String {
        return context.getString(R.string.expense_screen_date_time_title)
    }

    override suspend fun descriptionTitle(): String {
        return context.getString(R.string.expense_screen_description_title)
    }

    override suspend fun descriptionLabel(): String {
        return context.getString(R.string.expense_screen_description_label)
    }
}