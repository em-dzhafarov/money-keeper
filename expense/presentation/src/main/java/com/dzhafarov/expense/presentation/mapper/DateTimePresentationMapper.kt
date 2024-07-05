package com.dzhafarov.expense.presentation.mapper

import com.dzhafarov.core.presentation.UIMapper
import com.dzhafarov.date_time.domain.Timestamp
import com.dzhafarov.expense.presentation.ExpenseStringProvider
import javax.inject.Inject

class DateTimePresentationMapper @Inject constructor(
    private val stringProvider: ExpenseStringProvider
) : UIMapper<Timestamp, String> {

    override suspend fun map(input: Timestamp): String {
        val now = Timestamp.now()

        return when {
            Timestamp.areDatesEqual(now, input) -> {
               stringProvider.todayAtTime(input.formattedTime)
            }
            Timestamp.areDatesEqual(now.previousDate, input) -> {
               stringProvider.yesterdayAtTime(input.formattedTime)
            }
            else -> {
                stringProvider.atDateAndAtTime(input.formattedDate, input.formattedTime)
            }
        }
    }
}
