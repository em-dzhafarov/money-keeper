package com.dzhafarov.moneykeeper.filter.di

import com.dzhafarov.moneykeeper.filter.presentation.FilterStringProvider
import com.dzhafarov.moneykeeper.filter.presentation.FilterStringProviderImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FilterModule {

    @Binds
    fun bindFilterStringProvider(actual: FilterStringProviderImpl): FilterStringProvider

    companion object {
        @Provides
        @Reusable
        fun provideGson(): Gson = Gson()
    }
}