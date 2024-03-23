package com.dzhafarov.filters.domain.mapper

import com.dzhafarov.core.domain.mapper.Mapper
import com.dzhafarov.filters.domain.FilterData
import com.dzhafarov.filters.domain.parser.FilterDataParser
import javax.inject.Inject

class FilterDataMapper @Inject constructor(
    private val filterDataParser: FilterDataParser
) : Mapper<FilterData?, String> {

    override suspend fun to(input: FilterData?): String {
        return filterDataParser.wrap(input)
    }

    override suspend fun from(input: String): FilterData? {
        return filterDataParser.unwrap(input, FilterData::class.java)
    }
}
