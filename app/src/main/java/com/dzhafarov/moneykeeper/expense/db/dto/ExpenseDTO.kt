package com.dzhafarov.moneykeeper.expense.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val EXPENSES_TABLE_NAME = "expenses"

@Entity(tableName = EXPENSES_TABLE_NAME)
data class ExpenseDTO(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val amount: Double,
    val method: PaymentMethodDTO,
    val reason: PaymentReasonDTO,
    val currency: CurrencyDTO,
    val description: String,
    val time: Long
)
