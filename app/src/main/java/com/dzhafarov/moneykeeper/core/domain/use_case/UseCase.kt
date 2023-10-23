package com.dzhafarov.moneykeeper.core.domain.use_case

interface UseCase<INPUT, OUTPUT> {
    suspend fun execute(input: INPUT): OUTPUT
}

suspend fun <OUTPUT> UseCase<Nothing?, OUTPUT>.execute(): OUTPUT {
    return execute(null)
}