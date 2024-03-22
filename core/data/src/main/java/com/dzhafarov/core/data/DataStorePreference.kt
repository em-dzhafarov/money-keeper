package com.dzhafarov.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

abstract class DataStorePreference<T>(context: Context) {
    private val store: DataStore<Preferences> = context.dataStore
    private val _key by lazy { key.toPreferenceKey() }

    protected abstract val key: Key<T>

    open suspend fun edit(input: T) {
        store.edit {
            it[_key] = input
        }
    }

    open suspend fun clear() {
        store.edit { it.remove(_key) }
    }

    open fun observe(): Flow<T?> {
        return store.data.map { it[_key] }
    }

    suspend fun get(): T? {
        return observe().firstOrNull()
    }

    suspend fun getOrDefault(default: T): T {
        return get() ?: default
    }

    suspend fun getOrElse(provider: suspend () -> T): T {
        return get() ?: provider.invoke()
    }
}
