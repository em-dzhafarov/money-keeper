package com.dzhafarov.filters.data.mapper

import com.dzhafarov.core.data.mapper.DTOMapper
import com.dzhafarov.filters.data.parser.FilterDataParser
import com.dzhafarov.filters.domain.FilterData
import javax.inject.Inject

internal class FilterDataMapper @Inject constructor(
    private val filterDataParser: FilterDataParser
) : DTOMapper<FilterData?, String> {

    override suspend fun to(input: FilterData?): String {
        return filterDataParser.wrap(input)
    }

    override suspend fun from(input: String): FilterData? {
        return filterDataParser.unwrap(input, FilterData::class.java)
    }
}
