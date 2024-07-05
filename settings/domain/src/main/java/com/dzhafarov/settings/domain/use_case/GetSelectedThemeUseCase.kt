package com.dzhafarov.settings.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.settings.domain.ThemeRepository
import com.dzhafarov.settings.domain.model.Theme
import javax.inject.Inject

class GetSelectedThemeUseCase @Inject constructor(
    private val repository: ThemeRepository
) : UseCaseSuspend<Nothing?, Theme> {

    override suspend fun execute(input: Nothing?): Theme {
        return repository.getSelectedTheme()
    }
}
