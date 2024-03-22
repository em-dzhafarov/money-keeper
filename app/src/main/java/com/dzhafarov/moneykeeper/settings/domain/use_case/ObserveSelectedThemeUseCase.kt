package com.dzhafarov.moneykeeper.settings.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCase
import com.dzhafarov.moneykeeper.settings.domain.mapper.ThemeMapper
import com.dzhafarov.moneykeeper.settings.domain.model.Theme
import com.dzhafarov.moneykeeper.settings.domain.model.ThemeType
import com.dzhafarov.moneykeeper.settings.storage.IsDynamicThemePreference
import com.dzhafarov.moneykeeper.settings.storage.ThemeTypePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveSelectedThemeUseCase @Inject constructor(
    private val themeTypePreference: ThemeTypePreference,
    private val isDynamicThemePreference: IsDynamicThemePreference,
    private val themeMapper: ThemeMapper
) : UseCase<Nothing?, Flow<Theme>> {

    override fun execute(input: Nothing?): Flow<Theme> {
        return themeTypePreference.observe()
            .map(ThemeType::of)
            .combine(isDynamicThemePreference.observe(), ::Pair)
            .map(themeMapper::map)
    }
}