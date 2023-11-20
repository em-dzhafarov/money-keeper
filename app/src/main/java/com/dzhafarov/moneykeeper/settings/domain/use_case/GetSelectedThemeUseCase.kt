package com.dzhafarov.moneykeeper.settings.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.settings.domain.mapper.ThemeMapper
import com.dzhafarov.moneykeeper.settings.domain.model.Theme
import com.dzhafarov.moneykeeper.settings.domain.model.ThemeType
import com.dzhafarov.moneykeeper.settings.storage.IsDynamicThemePreference
import com.dzhafarov.moneykeeper.settings.storage.ThemeTypePreference
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
