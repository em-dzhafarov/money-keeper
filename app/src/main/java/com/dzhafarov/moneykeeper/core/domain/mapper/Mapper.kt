package com.dzhafarov.moneykeeper.core.domain.mapper

interface Mapper<INPUT, OUTPUT> {
    suspend fun to(input: INPUT): OUTPUT
    suspend fun from(input: OUTPUT): INPUT
}