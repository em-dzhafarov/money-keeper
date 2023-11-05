package com.dzhafarov.moneykeeper.date_time.domain

import java.sql.Time
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@JvmInline
value class Timestamp(private val value: Long) {

    val hours: Int get() = zonedDateTime().hour

    val minutes: Int get() = zonedDateTime().minute

    val seconds: Int get() = zonedDateTime().second

    val milliseconds: Long get() = value

    val formattedTime: String get() = format(defaultTimeFormatter)

    val formattedDate: String get() = format(defaultDateFormatter)

    val localDateTime: LocalDateTime
        get() = LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault())

    val previousDate: Timestamp
        get() {
            val ldt = localDateTime
            return of(ldt.toLocalDate().minusDays(1).atTime(ldt.toLocalTime()))
        }

    companion object {
        private const val DEFAULT_TIME_PATTERN = "hh:mm a"
        private const val DEFAULT_DATE_PATTERN = "dd MMM yyyy"

        private val defaultTimeFormatter by lazy {
            DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN)
        }

        private val defaultDateFormatter by lazy {
            DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)
        }

        private fun Timestamp.format(formatter: DateTimeFormatter): String {
            return zonedDateTime().format(formatter)
        }

        private fun Timestamp.zonedDateTime(zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime {
            return Instant.ofEpochMilli(value).atZone(zoneId)
        }

        fun of(dateMillis: Long, hours: Int = 0, minutes: Int = 0, seconds: Int = 0): Timestamp {
            val time = LocalTime.of(hours, minutes, seconds)
            val date = Instant.ofEpochMilli(dateMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            return of(
                milliseconds = LocalDateTime.of(date, time)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            )
        }

        fun now(): Timestamp = of(Instant.now().toEpochMilli())

        fun of(dateTime: LocalDateTime): Timestamp {
            return of(
                milliseconds = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            )
        }

        fun of(hours: Int = 0, minutes: Int = 0, seconds: Int = 0): Timestamp {
            return of(
                milliseconds = LocalTime.of(hours, minutes, seconds)
                    .atDate(LocalDate.ofEpochDay(1))
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            )
        }

        fun of(milliseconds: Long): Timestamp {
            return Timestamp(milliseconds)
        }

        fun areDatesEqual(first: Timestamp, second: Timestamp): Boolean {
            return first.localDateTime.toLocalDate().isEqual(second.localDateTime.toLocalDate())
        }
    }
}