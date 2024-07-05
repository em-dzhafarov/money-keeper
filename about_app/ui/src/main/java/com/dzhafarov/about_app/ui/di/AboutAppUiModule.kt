package com.dzhafarov.about_app.ui.di

import com.dzhafarov.about_app.presentation.AboutAppStringProvider
import com.dzhafarov.about_app.ui.AboutAppStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module(includes = [AboutAppUiInternalModule::class])
@DisableInstallInCheck
interface AboutAppUiModule

@Module
@DisableInstallInCheck
internal interface AboutAppUiInternalModule {

    @Binds
    fun bindAboutAppStringProvider(actual: AboutAppStringProviderImpl): AboutAppStringProvider
}