package com.dzhafarov.moneykeeper.settings.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.dzhafarov.moneykeeper.core.app.storage.DataStorePreference
import com.dzhafarov.moneykeeper.core.app.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IsDynamicThemePreference @Inject constructor(
    @ApplicationContext context: Context
) : DataStorePreference<Boolean>(context.dataStore) {

    override val key = booleanPreferencesKey("IS_DYNAMIC_THEME")
}
