package com.dzhafarov.moneykeeper.date_time.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class DateTimeSelectorStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DateTimeSelectorStringProvider {

    override suspend fun dateTitle(): String {
        return context.getString(R.string.dialog_date_title)
    }

    override suspend fun timeTitle(): String {
        return context.getString(R.string.dialog_time_title)
    }

    override suspend fun confirmButton(): String {
        return context.getString(R.string.dialog_date_time_confirm)
    }

    override suspend fun cancelButton(): String {
        return context.getString(R.string.dialog_date_time_cancel)
    }
}
