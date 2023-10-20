package com.dzhafarov.moneykeeper.expense.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dzhafarov.moneykeeper.expense.db.dao.ExpenseDao
import com.dzhafarov.moneykeeper.expense.db.dto.ExpenseDTO

@Database(entities = [ExpenseDTO::class], version = 1, exportSchema = false)
abstract class MoneyKeeperDatabase : RoomDatabase() {

    abstract fun expensesDao(): ExpenseDao

    companion object {
        internal const val DB_NAME = "MoneyKeeper"
    }
}