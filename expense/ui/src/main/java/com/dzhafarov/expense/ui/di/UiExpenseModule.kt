package com.dzhafarov.expense.ui.di

import com.dzhafarov.expense.presentation.DrawableResourceProvider
import com.dzhafarov.expense.presentation.ExpenseStringProvider
import com.dzhafarov.expense.ui.DrawableResourceProviderImpl
import com.dzhafarov.expense.ui.ExpenseStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [UiExpenseInternalModule::class])
@DisableInstallInCheck
interface UiExpenseModule

@Module
@DisableInstallInCheck
internal interface UiExpenseInternalModule {
    @Binds
    fun bindExpenseStringProvider(actual: ExpenseStringProviderImpl): ExpenseStringProvider

    @Binds
    fun bindDrawableResourceProvider(actual: DrawableResourceProviderImpl): DrawableResourceProvider
}