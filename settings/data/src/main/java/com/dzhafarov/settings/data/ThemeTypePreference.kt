package com.dzhafarov.settings.data

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import com.dzhafarov.core.data.DataStorePreference
import com.dzhafarov.core.data.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ThemeTypePreference @Inject constructor(
    @ApplicationContext context: Context
) : DataStorePreference<Int>(context.dataStore) {

    override val key = intPreferencesKey("THEME_TYPE")
}
