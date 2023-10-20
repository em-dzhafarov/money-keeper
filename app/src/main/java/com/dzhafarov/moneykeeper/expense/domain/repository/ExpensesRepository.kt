package com.dzhafarov.moneykeeper.expense.domain.repository

import com.dzhafarov.moneykeeper.expense.domain.model.Expense

interface ExpensesRepository {

    suspend fun getAll(): List<Expense>

    suspend fun insert(expense: Expense)

    suspend fun insert(expenses: List<Expense>)

    suspend fun delete(expense: Expense)

    suspend fun delete(expenses: List<Expense>)

    suspend fun update(expense: Expense)

    suspend fun update(expenses: List<Expense>)

    suspend fun clear()
}