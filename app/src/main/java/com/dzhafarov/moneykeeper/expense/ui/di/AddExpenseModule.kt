package com.dzhafarov.moneykeeper.expense.ui.di

import com.dzhafarov.moneykeeper.expense.ui.presentation.AddExpenseStringProvider
import com.dzhafarov.moneykeeper.expense.ui.presentation.AddExpenseStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AddExpenseModule {

    @Binds
    fun bindAddExpenseStringProvider(actual: AddExpenseStringProviderImpl): AddExpenseStringProvider
}