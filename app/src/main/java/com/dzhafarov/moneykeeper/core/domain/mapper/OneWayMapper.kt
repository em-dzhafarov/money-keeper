package com.dzhafarov.moneykeeper.core.domain.mapper

interface OneWayMapper<INPUT, OUTPUT> {
    suspend fun map(input: INPUT): OUTPUT
}