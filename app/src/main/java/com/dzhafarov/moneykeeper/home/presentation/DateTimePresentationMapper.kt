package com.dzhafarov.moneykeeper.home.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import com.dzhafarov.core.domain.mapper.OneWayMapper
import com.dzhafarov.date_time.domain.Timestamp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DateTimePresentationMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : OneWayMapper<com.dzhafarov.date_time.domain.Timestamp, String> {

    override suspend fun map(input: com.dzhafarov.date_time.domain.Timestamp): String {
        val now = com.dzhafarov.date_time.domain.Timestamp.now()

        return when {
            com.dzhafarov.date_time.domain.Timestamp.areDatesEqual(now, input) -> {
                context.getString(R.string.label_today) + " " +
                        context.getString(R.string.label_at_time, input.formattedTime)
            }

            com.dzhafarov.date_time.domain.Timestamp.areDatesEqual(now.previousDate, input) -> {
                context.getString(R.string.label_yesterday) + " " +
                        context.getString(R.string.label_at_time, input.formattedTime)
            }

            else -> {
                input.formattedDate + " " +
                        context.getString(R.string.label_at_time, input.formattedTime)
            }
        }
    }
}
