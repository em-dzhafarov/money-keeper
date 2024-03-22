package com.dzhafarov.home.di

import com.dzhafarov.home.ui.HomeStringProviderImpl
import com.dzhafarov.home.presentation.HomeStringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HomeModule {

    @Binds
    fun bindHomeStringProvider(actual: HomeStringProviderImpl): HomeStringProvider
}
