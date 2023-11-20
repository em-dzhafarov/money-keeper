package com.dzhafarov.moneykeeper.core.app.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

abstract class DataStorePreference<T>(
    private val store: DataStore<Preferences>
) {

    protected abstract val key: Preferences.Key<T>

    open suspend fun edit(input: T) {
        store.edit {
            it[key] = input
        }
    }

    open suspend fun clear() {
        store.edit { it.remove(key) }
    }

    open fun observe(): Flow<T?> {
        return store.data.map { it[key] }
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
