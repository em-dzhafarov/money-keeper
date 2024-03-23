package com.dzhafarov.core.data

import com.google.gson.Gson

open class JsonParser<T> {
    private val gson by lazy { Gson() }

    open fun wrap(input: T?): String {
        return try {
            gson.toJson(input)
        } catch (e: RuntimeException) {
            ""
        }
    }

    open fun unwrap(input: String, theClass: Class<T>): T? {
        return try {
            gson.fromJson(input, theClass)
        } catch (e: RuntimeException) {
            null
        }
    }
}