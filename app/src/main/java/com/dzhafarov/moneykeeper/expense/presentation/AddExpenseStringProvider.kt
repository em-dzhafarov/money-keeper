package com.dzhafarov.moneykeeper.expense.presentation

interface AddExpenseStringProvider {
    suspend fun title(): String
    suspend fun paymentReasonTitle(): String
    suspend fun paymentReasonError(): String
    suspend fun paymentMethodTitle(): String
    suspend fun paymentMethodError(): String
    suspend fun amountTitle(): String
    suspend fun saveTitle(): String
    suspend fun amountLabel(): String
    suspend fun amountError(): String
    suspend fun dateTimeTitle(): String
    suspend fun descriptionTitle(): String
    suspend fun descriptionLabel(): String
}