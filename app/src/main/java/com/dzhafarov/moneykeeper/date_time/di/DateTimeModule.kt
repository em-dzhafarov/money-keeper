package com.dzhafarov.moneykeeper.date_time.di

import com.dzhafarov.moneykeeper.date_time.presentation.DateTimeSelectorStringProvider
import com.dzhafarov.moneykeeper.date_time.presentation.DateTimeSelectorStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DateTimeModule {

    @Binds
    fun bindDateTimeStringProvider(actual: DateTimeSelectorStringProviderImpl): DateTimeSelectorStringProvider
}