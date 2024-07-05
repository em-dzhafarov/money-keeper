package com.dzhafarov.core.data.mapper

interface DTOMapper<INPUT, OUTPUT> {
    suspend fun to(input: INPUT): OUTPUT
    suspend fun from(input: OUTPUT): INPUT
}
