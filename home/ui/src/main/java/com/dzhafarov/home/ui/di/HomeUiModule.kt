package com.dzhafarov.home.ui.di

import com.dzhafarov.home.presentation.HomeStringProvider
import com.dzhafarov.home.ui.HomeStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [HomeUiInternalModule::class])
@DisableInstallInCheck
interface HomeUiModule

@Module
@DisableInstallInCheck
internal interface HomeUiInternalModule {
    @Binds
    fun bindHomeStringProvider(actual: HomeStringProviderImpl): HomeStringProvider
}
