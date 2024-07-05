package com.dzhafarov.date_time.ui

import android.content.Context
import com.dzhafarov.date_time.presentation.DateTimeSelectorStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class DateTimeSelectorStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DateTimeSelectorStringProvider {

    override val dateTitle: String
        get() = context.getString(R.string.dialog_date_title)

    override val timeTitle: String
        get() = context.getString(R.string.dialog_time_title)

    override val confirmButton: String
        get() = context.getString(R.string.dialog_date_time_confirm)

    override val cancelButton: String
        get() = context.getString(R.string.dialog_date_time_cancel)
}
