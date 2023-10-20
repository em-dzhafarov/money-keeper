package com.dzhafarov.moneykeeper.expense.di

import android.content.Context
import androidx.room.Room
import com.dzhafarov.moneykeeper.expense.db.MoneyKeeperDatabase
import com.dzhafarov.moneykeeper.expense.db.dao.ExpenseDao
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepository
import com.dzhafarov.moneykeeper.expense.domain.repository.ExpensesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ExpenseModule {

    @Binds
    fun bindExpensesRepository(actual: ExpensesRepositoryImpl): ExpensesRepository

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): MoneyKeeperDatabase {
            return Room
                .databaseBuilder(context, MoneyKeeperDatabase::class.java, MoneyKeeperDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        fun provideExpensesDao(db: MoneyKeeperDatabase): ExpenseDao {
            return db.expensesDao()
        }
    }
}