package com.dzhafarov.moneykeeper.about.di

import com.dzhafarov.moneykeeper.about.presentation.AboutAppStringProvider
import com.dzhafarov.moneykeeper.about.presentation.AboutAppStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AboutAppModule {

    @Binds
    fun bindAboutAppStringProvider(actual: AboutAppStringProviderImpl): AboutAppStringProvider
}