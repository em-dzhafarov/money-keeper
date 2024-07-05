package com.dzhafarov.date_time.di

import com.dzhafarov.date_time.ui.di.DateTimeUiModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DateTimeUiModule::class])
@InstallIn(SingletonComponent::class)
interface DateTimeModule