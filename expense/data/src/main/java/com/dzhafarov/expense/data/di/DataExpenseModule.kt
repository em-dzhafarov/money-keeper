package com.dzhafarov.expense.data.di

import android.content.Context
import androidx.room.Room
import com.dzhafarov.expense.data.db.ExpenseDao
import com.dzhafarov.expense.data.db.ExpensesDatabase
import com.dzhafarov.expense.data.repository.CurrencyRepositoryImpl
import com.dzhafarov.expense.data.repository.ExpensesRepositoryImpl
import com.dzhafarov.expense.data.repository.PaymentMethodRepositoryImpl
import com.dzhafarov.expense.data.repository.PaymentReasonRepositoryImpl
import com.dzhafarov.expense.domain.repository.CurrencyRepository
import com.dzhafarov.expense.domain.repository.ExpensesRepository
import com.dzhafarov.expense.domain.repository.PaymentMethodRepository
import com.dzhafarov.expense.domain.repository.PaymentReasonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module(includes = [DataExpenseInternalModule::class])
@DisableInstallInCheck
interface DataExpenseModule

@Module
@DisableInstallInCheck
internal interface DataExpenseInternalModule {
    @Binds
    fun bindExpensesRepository(actual: ExpensesRepositoryImpl): ExpensesRepository

    @Binds
    fun bindPaymentReasonRepository(actual: PaymentReasonRepositoryImpl): PaymentReasonRepository

    @Binds
    fun bindPaymentMethodRepository(actual: PaymentMethodRepositoryImpl): PaymentMethodRepository

    @Binds
    fun bindCurrencyRepository(actual: CurrencyRepositoryImpl): CurrencyRepository

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): ExpensesDatabase {
            return Room
                .databaseBuilder(
                    context,
                    ExpensesDatabase::class.java,
                    ExpensesDatabase.DB_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        fun provideExpensesDao(db: ExpensesDatabase): ExpenseDao {
            return db.expensesDao()
        }
    }
}