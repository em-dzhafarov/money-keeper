package com.dzhafarov.about_app.ui

import android.content.Context
import com.dzhafarov.about_app.presentation.AboutAppStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AboutAppStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AboutAppStringProvider {

    override val title: String
        get() = context.getString(R.string.dialog_about_app_title)

    override val text: String
        get() = context.getString(R.string.dialog_about_app_text)

    override val confirm: String
        get() = context.getString(R.string.dialog_about_app_text)
}
