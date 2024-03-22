package com.dzhafarov.moneykeeper.settings.domain.mapper

import com.dzhafarov.core.domain.mapper.OneWayMapper
import com.dzhafarov.core.domain.use_case.execute
import com.dzhafarov.moneykeeper.settings.domain.use_case.IsDynamicColorsSupportedUseCase
import com.dzhafarov.moneykeeper.settings.domain.model.Theme
import com.dzhafarov.moneykeeper.settings.domain.model.ThemeType
import javax.inject.Inject

class ThemeMapper @Inject constructor(
    private val isDynamicColorsSupportedUseCase: IsDynamicColorsSupportedUseCase
) : OneWayMapper<Pair<ThemeType?, Boolean?>, Theme> {

    override suspend fun map(input: Pair<ThemeType?, Boolean?>): Theme {
        val (type, isDynamic) = input

        val default = Theme(
            type = ThemeType.Default,
            isDynamic = isDynamicColorsSupportedUseCase.execute()
        )

        return when {
            type == null && isDynamic == null -> default
            type != null && isDynamic == null -> default.copy(type = type)
            type == null && isDynamic != null -> default.copy(isDynamic = isDynamic)
            type != null && isDynamic != null -> Theme(type = type, isDynamic = isDynamic)
            else -> default
        }
    }
}