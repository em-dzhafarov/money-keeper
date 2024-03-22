package com.dzhafarov.core.domain.mapper

interface OneWayMapper<INPUT, OUTPUT> {
    suspend fun map(input: INPUT): OUTPUT
}
