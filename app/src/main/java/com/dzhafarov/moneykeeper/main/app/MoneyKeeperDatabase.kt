package com.dzhafarov.moneykeeper.main.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dzhafarov.expense.data.dao.ExpenseDao
import com.dzhafarov.expense.data.dto.ExpenseDTO

@Database(entities = [ExpenseDTO::class], version = 1, exportSchema = false)
abstract class MoneyKeeperDatabase : RoomDatabase() {

    abstract fun expensesDao(): ExpenseDao

    companion object {
        internal const val DB_NAME = "MoneyKeeper"
    }
}