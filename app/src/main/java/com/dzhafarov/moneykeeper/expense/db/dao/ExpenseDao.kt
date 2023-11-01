package com.dzhafarov.moneykeeper.expense.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dzhafarov.moneykeeper.expense.db.dto.EXPENSES_TABLE_NAME
import com.dzhafarov.moneykeeper.expense.db.dto.ExpenseDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM $EXPENSES_TABLE_NAME")
    suspend fun getAll(): List<ExpenseDTO>

    @Query("SELECT * FROM $EXPENSES_TABLE_NAME")
    fun observeAll(): Flow<List<ExpenseDTO>>

    @Query("SELECT * FROM $EXPENSES_TABLE_NAME WHERE id=:id")
    suspend fun findById(id: Long): ExpenseDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: ExpenseDTO)

    @Delete
    suspend fun delete(expense: ExpenseDTO)

    @Query("DELETE FROM $EXPENSES_TABLE_NAME")
    suspend fun clear()
}
