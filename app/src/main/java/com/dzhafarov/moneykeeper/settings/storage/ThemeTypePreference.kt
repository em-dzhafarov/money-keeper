package com.dzhafarov.moneykeeper.settings.storage

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import com.dzhafarov.moneykeeper.core.app.storage.DataStorePreference
import com.dzhafarov.moneykeeper.core.utils.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ThemeTypePreference @Inject constructor(
    @ApplicationContext context: Context
) : DataStorePreference<Int>(context.dataStore) {

    override val key = intPreferencesKey("THEME_TYPE")
}
