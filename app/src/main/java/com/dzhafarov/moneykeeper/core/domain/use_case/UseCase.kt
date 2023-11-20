package com.dzhafarov.moneykeeper.core.domain.use_case

interface UseCase<INPUT, OUTPUT> {
    fun execute(input: INPUT): OUTPUT
}

fun <OUTPUT> UseCase<Nothing?, OUTPUT>.execute(): OUTPUT {
    return execute(null)
}