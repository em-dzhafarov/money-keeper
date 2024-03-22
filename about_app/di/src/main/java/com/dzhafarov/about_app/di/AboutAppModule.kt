package com.dzhafarov.about_app.di

import com.dzhafarov.about_app.presentation.AboutAppStringProvider
import com.dzhafarov.about_app.ui.AboutAppStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface AboutAppModule {

    @Binds
    fun bindAboutAppStringProvider(actual: AboutAppStringProviderImpl): AboutAppStringProvider
}