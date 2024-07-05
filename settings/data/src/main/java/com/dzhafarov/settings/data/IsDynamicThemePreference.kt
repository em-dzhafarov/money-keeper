package com.dzhafarov.settings.data

import android.content.Context
import com.dzhafarov.core.data.DataStorePreference
import com.dzhafarov.core.data.Key
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class IsDynamicThemePreference @Inject constructor(
    @ApplicationContext context: Context
) : DataStorePreference<Boolean>(context) {

    override val key = Key.OfBoolean("IS_DYNAMIC_THEME")
}
