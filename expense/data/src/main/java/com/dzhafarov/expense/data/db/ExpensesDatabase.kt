package com.dzhafarov.expense.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExpenseDTO::class], version = 1, exportSchema = false)
internal abstract class ExpensesDatabase : RoomDatabase() {

    abstract fun expensesDao(): ExpenseDao

    companion object {
        internal const val DB_NAME = "money_keeper.db.expenses"
    }
}