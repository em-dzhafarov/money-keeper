package com.dzhafarov.expense.di

import com.dzhafarov.expense.data.di.DataExpenseModule
import com.dzhafarov.expense.ui.di.UiExpenseModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DataExpenseModule::class, UiExpenseModule::class])
@InstallIn(SingletonComponent::class)
interface ExpenseModule