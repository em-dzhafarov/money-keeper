package com.dzhafarov.settings.data.mapper

import com.dzhafarov.core.data.mapper.OneWayMapper
import com.dzhafarov.settings.domain.model.Theme
import com.dzhafarov.settings.domain.model.ThemeType
import javax.inject.Inject

internal class ThemeMapper @Inject constructor() : OneWayMapper<Pair<ThemeType?, Boolean?>, Theme> {

    override suspend fun map(input: Pair<ThemeType?, Boolean?>): Theme {
        val (type, isDynamic) = input

        return when {
            type == null && isDynamic == null -> Theme.Default
            type != null && isDynamic == null -> Theme.Default.copy(type = type)
            type == null && isDynamic != null -> Theme.Default.copy(isDynamic = isDynamic)
            type != null && isDynamic != null -> Theme(type = type, isDynamic = isDynamic)
            else -> Theme.Default
        }
    }
}