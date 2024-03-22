package com.dzhafarov.settings.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.settings.data.IsDynamicThemePreference
import com.dzhafarov.settings.data.ThemeTypePreference
import com.dzhafarov.settings.domain.mapper.ThemeMapper
import com.dzhafarov.settings.domain.model.Theme
import com.dzhafarov.settings.domain.model.ThemeType
import javax.inject.Inject

class GetSelectedThemeUseCase @Inject constructor(
    private val themeTypePreference: ThemeTypePreference,
    private val isDynamicThemePreference: IsDynamicThemePreference,
    private val themeMapper: ThemeMapper
) : UseCaseSuspend<Nothing?, Theme> {

    override suspend fun execute(input: Nothing?): Theme {
        val type = themeTypePreference.get()?.let(ThemeType::of)
        val isDynamic = isDynamicThemePreference.get()

        return themeMapper.map(type to isDynamic)
    }
}
