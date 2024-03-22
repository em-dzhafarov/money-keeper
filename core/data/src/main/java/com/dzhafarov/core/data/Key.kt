package com.dzhafarov.core.data

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

sealed class Key<T>(val key: String) {
    class OfInt(key: String) : Key<Int>(key)
    class OfLong(key: String) : Key<Long>(key)
    class OfBoolean(key: String) : Key<Boolean>(key)
    class OfFloat(key: String) : Key<Float>(key)
    class OfDouble(key: String) : Key<Double>(key)
    class OfString(key: String) : Key<String>(key)
}

@Suppress("UNCHECKED_CAST")
internal fun <T> Key<T>.toPreferenceKey(): Preferences.Key<T> {
    return when (this) {
        is Key.OfBoolean -> booleanPreferencesKey(key)
        is Key.OfDouble -> doublePreferencesKey(key)
        is Key.OfFloat -> floatPreferencesKey(key)
        is Key.OfInt -> intPreferencesKey(key)
        is Key.OfLong -> longPreferencesKey(key)
        is Key.OfString -> stringPreferencesKey(key)
    } as Preferences.Key<T>
}
