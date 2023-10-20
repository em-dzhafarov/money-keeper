package com.dzhafarov.moneykeeper.home.di

import com.dzhafarov.moneykeeper.home.presentation.HomeStringProviderImpl
import com.dzhafarov.moneykeeper.home.presentation.HomeStringProvider
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
