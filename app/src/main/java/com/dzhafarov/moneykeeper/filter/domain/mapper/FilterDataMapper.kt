package com.dzhafarov.moneykeeper.filter.domain.mapper

import com.dzhafarov.core.domain.mapper.Mapper
import com.dzhafarov.moneykeeper.filter.presentation.FilterData
import com.google.gson.Gson
import javax.inject.Inject

class FilterDataMapper @Inject constructor(
    private val gson: Gson
) : Mapper<FilterData?, String> {

    override suspend fun to(input: FilterData?): String {
        return try {
            gson.toJson(input)
        } catch (e: RuntimeException) {
            ""
        }
    }

    override suspend fun from(input: String): FilterData? {
        return try {
            gson.fromJson(input, FilterData::class.java)
        } catch (e: RuntimeException) {
            null
        }
    }
}
