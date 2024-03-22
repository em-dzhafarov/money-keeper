package com.dzhafarov.expense.domain.repository

import com.dzhafarov.expense.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {

    suspend fun getAll(): List<Expense>

    fun observeAll(): Flow<List<Expense>>

    suspend fun insert(expense: Expense)

    suspend fun insert(expenses: List<Expense>)

    suspend fun delete(expense: Expense)

    suspend fun delete(expenses: List<Expense>)

    suspend fun update(expense: Expense)

    suspend fun update(expenses: List<Expense>)

    suspend fun findById(id: Long): Expense?

    suspend fun clear()

    suspend fun getMinAmount(): Double

    suspend fun getMaxAmount(): Double
}