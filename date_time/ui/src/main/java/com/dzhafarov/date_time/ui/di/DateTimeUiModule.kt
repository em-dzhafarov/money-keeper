package com.dzhafarov.date_time.ui.di

import com.dzhafarov.date_time.presentation.DateTimeSelectorStringProvider
import com.dzhafarov.date_time.ui.DateTimeSelectorStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [DateTimeUiInternalModule::class])
@DisableInstallInCheck
interface DateTimeUiModule

@Module
@DisableInstallInCheck
internal interface DateTimeUiInternalModule {
    @Binds
    fun bindDateTimeStringProvider(actual: DateTimeSelectorStringProviderImpl): DateTimeSelectorStringProvider
}