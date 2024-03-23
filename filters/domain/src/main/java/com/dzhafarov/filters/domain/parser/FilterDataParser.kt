package com.dzhafarov.filters.domain.parser

import com.dzhafarov.core.data.JsonParser
import com.dzhafarov.filters.domain.FilterData
import javax.inject.Inject

class FilterDataParser @Inject constructor() : JsonParser<FilterData>()