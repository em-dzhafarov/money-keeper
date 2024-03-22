package com.dzhafarov.filters.data

import android.content.Context
import com.dzhafarov.core.data.DataStorePreference
import com.dzhafarov.core.data.Key
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrentFiltersPreference @Inject constructor(
    @ApplicationContext context: Context
) : DataStorePreference<String>(context) {

    override val key = Key.OfString("CURRENT_FILTERS")
}
