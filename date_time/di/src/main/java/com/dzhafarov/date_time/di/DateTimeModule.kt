package com.dzhafarov.date_time.di

import com.dzhafarov.date_time.presentation.DateTimeSelectorStringProvider
import com.dzhafarov.date_time.ui.DateTimeSelectorStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DateTimeModule {

    @Binds
    fun bindDateTimeStringProvider(actual: DateTimeSelectorStringProviderImpl): DateTimeSelectorStringProvider
}