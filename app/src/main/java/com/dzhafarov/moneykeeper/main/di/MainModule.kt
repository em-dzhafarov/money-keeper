package com.dzhafarov.moneykeeper.main.di

import android.content.Context
import androidx.room.Room
import com.dzhafarov.expense.data.dao.ExpenseDao
import com.dzhafarov.moneykeeper.main.app.MoneyKeeperDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): MoneyKeeperDatabase {
            return Room
                .databaseBuilder(
                    context,
                    MoneyKeeperDatabase::class.java,
                    MoneyKeeperDatabase.DB_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        fun provideExpensesDao(db: MoneyKeeperDatabase): ExpenseDao {
            return db.expensesDao()
        }
    }
}