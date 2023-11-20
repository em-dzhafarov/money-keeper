package com.dzhafarov.moneykeeper.settings.domain.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.moneykeeper.settings.domain.model.Theme
import com.dzhafarov.moneykeeper.settings.storage.IsDynamicThemePreference
import com.dzhafarov.moneykeeper.settings.storage.ThemeTypePreference
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
