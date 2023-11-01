package com.dzhafarov.moneykeeper.date_time.presentation

interface DateTimeSelectorStringProvider {
    suspend fun dateTitle(): String
    suspend fun timeTitle(): String
    suspend fun confirmButton(): String
    suspend fun cancelButton(): String
}
