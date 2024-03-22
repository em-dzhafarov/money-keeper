package com.dzhafarov.settings.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.settings.domain.model.Theme
import com.dzhafarov.settings.data.IsDynamicThemePreference
import com.dzhafarov.settings.data.ThemeTypePreference
import javax.inject.Inject

class UpdateSelectedThemeUseCase @Inject constructor(
    private val themeTypePreference: ThemeTypePreference,
    private val isDynamicThemePreference: IsDynamicThemePreference
) : UseCaseSuspend<Theme, Unit> {

    override suspend fun execute(input: Theme) {
        themeTypePreference.edit(input.type.ordinal)
        isDynamicThemePreference.edit(input.isDynamic)
    }
}
