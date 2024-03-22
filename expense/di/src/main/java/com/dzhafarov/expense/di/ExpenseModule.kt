package com.dzhafarov.expense.di

import com.dzhafarov.expense.domain.repository.CurrencyRepository
import com.dzhafarov.expense.domain.repository.CurrencyRepositoryImpl
import com.dzhafarov.expense.domain.repository.ExpensesRepository
import com.dzhafarov.expense.domain.repository.ExpensesRepositoryImpl
import com.dzhafarov.expense.domain.repository.PaymentMethodRepository
import com.dzhafarov.expense.domain.repository.PaymentMethodRepositoryImpl
import com.dzhafarov.expense.domain.repository.PaymentReasonRepository
import com.dzhafarov.expense.domain.repository.PaymentReasonRepositoryImpl
import com.dzhafarov.expense.presentation.DrawableResourceProvider
import com.dzhafarov.expense.presentation.ExpenseStringProvider
import com.dzhafarov.expense.ui.DrawableResourceProviderImpl
import com.dzhafarov.expense.ui.ExpenseStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ExpenseModule {

    @Binds
    fun bindExpenseStringProvider(actual: ExpenseStringProviderImpl): ExpenseStringProvider

    @Binds
    fun bindDrawableResourceProvider(actual: DrawableResourceProviderImpl): DrawableResourceProvider

    @Binds
    fun bindExpensesRepository(actual: ExpensesRepositoryImpl): ExpensesRepository

    @Binds
    fun bindPaymentReasonRepository(actual: PaymentReasonRepositoryImpl): PaymentReasonRepository

    @Binds
    fun bindPaymentMethodRepository(actual: PaymentMethodRepositoryImpl): PaymentMethodRepository

    @Binds
    fun bindCurrencyRepository(actual: CurrencyRepositoryImpl): CurrencyRepository
}