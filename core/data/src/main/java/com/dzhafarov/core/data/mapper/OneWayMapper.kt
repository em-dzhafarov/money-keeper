package com.dzhafarov.core.data.mapper

interface OneWayMapper<INPUT, OUTPUT> {
    suspend fun map(input: INPUT): OUTPUT
}
