package com.dzhafarov.home.di

import com.dzhafarov.home.ui.di.HomeUiModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [HomeUiModule::class])
@InstallIn(SingletonComponent::class)
interface HomeModule
