package com.dzhafarov.about_app.di

import com.dzhafarov.about_app.ui.di.AboutAppUiModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AboutAppUiModule::class])
@InstallIn(SingletonComponent::class)
interface AboutAppModule