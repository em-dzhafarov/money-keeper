package com.dzhafarov.moneykeeper.about.presentation

import android.content.Context
import com.dzhafarov.moneykeeper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AboutAppStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AboutAppStringProvider {

    override suspend fun title(): String {
        return context.getString(R.string.dialog_about_app_title)
    }

    override suspend fun text(): String {
        return context.getString(R.string.dialog_about_app_text)
    }

    override suspend fun confirm(): String {
        return context.getString(R.string.dialog_about_app_confirm_button)
    }
}
