package com.dzhafarov.moneykeeper.home.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import com.dzhafarov.moneykeeper.core.domain.mapper.OneWayMapper
import com.dzhafarov.moneykeeper.date_time.domain.Timestamp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DateTimePresentationMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : OneWayMapper<Timestamp, String> {

    override suspend fun map(input: Timestamp): String {
        val now = Timestamp.now()

        return when {
            Timestamp.areDatesEqual(now, input) -> {
                context.getString(R.string.label_today) + " " +
                        context.getString(R.string.label_at_time, input.formattedTime)
            }
            Timestamp.areDatesEqual(now.previousDate, input) -> {
                context.getString(R.string.label_yesterday) + " " +
                        context.getString(R.string.label_at_time, input.formattedTime)
            }

            else -> context.getString(R.string.label_on_date, input.formattedDate) + " " +
                    context.getString(R.string.label_at_time, input.formattedTime)
        }
    }
}
