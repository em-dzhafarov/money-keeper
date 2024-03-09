package com.dzhafarov.moneykeeper.expense.domain.repository

import com.dzhafarov.moneykeeper.expense.db.dao.ExpenseDao
import com.dzhafarov.moneykeeper.expense.domain.mapper.ExpenseMapper
import com.dzhafarov.moneykeeper.expense.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpensesRepositoryImpl @Inject constructor(
    private val expensesDao: ExpenseDao,
    private val expenseMapper: ExpenseMapper
) : ExpensesRepository {

    override suspend fun getAll(): List<Expense> {
        return expensesDao.getAll().map { dto ->
            expenseMapper.to(dto)
        }
    }

    override fun observeAll(): Flow<List<Expense>> {
        return expensesDao.observeAll()
            .map { dtoItems ->
                dtoItems.map { dto ->
                    expenseMapper.to(dto)
                }
            }
    }

    override suspend fun insert(expense: Expense) {
        expensesDao.insert(
            expenseMapper.from(expense)
        )
    }

    override suspend fun insert(expenses: List<Expense>) {
        expenses.forEach { insert(it) }
    }

    override suspend fun delete(expense: Expense) {
        expensesDao.delete(
            expenseMapper.from(expense)
        )
    }

    override suspend fun delete(expenses: List<Expense>) {
        expenses.forEach { delete(it) }
    }

    override suspend fun update(expense: Expense) {
        val expenseDto = expenseMapper.from(expense)
        val current = expensesDao.findById(expense.id)

        if (current == null || current != expenseDto) {
            expensesDao.insert(expenseDto)
        }
    }

    override suspend fun update(expenses: List<Expense>) {
        expenses.forEach { update(it) }
    }

    override suspend fun findById(id: Long): Expense? {
        return expensesDao.findById(id)?.let {
            expenseMapper.to(it)
        }
    }

    override suspend fun clear() {
        expensesDao.clear()
    }

    override suspend fun getMinAmount(): Double {
        return expensesDao.getMinAmount()
    }

    override suspend fun getMaxAmount(): Double {
        return expensesDao.getMaxAmount()
    }
}