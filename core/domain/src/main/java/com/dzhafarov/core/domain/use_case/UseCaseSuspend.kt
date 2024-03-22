package com.dzhafarov.core.domain.use_case

interface UseCaseSuspend<INPUT, OUTPUT> {
    suspend fun execute(input: INPUT): OUTPUT
}

suspend fun <OUTPUT> UseCaseSuspend<Nothing?, OUTPUT>.execute(): OUTPUT {
    return execute(null)
}
