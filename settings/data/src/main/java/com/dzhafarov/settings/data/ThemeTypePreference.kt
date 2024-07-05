package com.dzhafarov.settings.data

import android.content.Context
import com.dzhafarov.core.data.DataStorePreference
import com.dzhafarov.core.data.Key
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class ThemeTypePreference @Inject constructor(
    @ApplicationContext context: Context
) : DataStorePreference<Int>(context) {

    override val key = Key.OfInt("THEME_TYPE")
}
