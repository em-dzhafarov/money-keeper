package com.dzhafarov.core.presentation

interface UIMapper<INPUT, OUTPUT> {
    suspend fun map(input: INPUT): OUTPUT
}