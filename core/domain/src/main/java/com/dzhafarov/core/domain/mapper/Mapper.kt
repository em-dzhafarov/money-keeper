package com.dzhafarov.core.domain.mapper

interface Mapper<INPUT, OUTPUT> {
    suspend fun to(input: INPUT): OUTPUT
    suspend fun from(input: OUTPUT): INPUT
}
