package com.dzhafarov.settings.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.settings.domain.model.Theme
import com.dzhafarov.settings.domain.ThemeRepository
import javax.inject.Inject

class UpdateSelectedThemeUseCase @Inject constructor(
    private val repository: ThemeRepository
) : UseCaseSuspend<Theme, Unit> {

    override suspend fun execute(input: Theme) {
        repository.save(input)
    }
}
