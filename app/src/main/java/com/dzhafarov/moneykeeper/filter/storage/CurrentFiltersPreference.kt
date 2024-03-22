package com.dzhafarov.moneykeeper.filter.storage

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dzhafarov.core.data.DataStorePreference
import com.dzhafarov.core.data.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrentFiltersPreference @Inject constructor(
    @ApplicationContext context: Context
) : com.dzhafarov.core.data.DataStorePreference<String>(context.dataStore) {

    override val key = stringPreferencesKey("CURRENT_FILTERS")
}
