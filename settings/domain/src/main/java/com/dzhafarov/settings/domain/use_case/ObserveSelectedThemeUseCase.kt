package com.dzhafarov.settings.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCase
import com.dzhafarov.settings.domain.ThemeRepository
import com.dzhafarov.settings.domain.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveSelectedThemeUseCase @Inject constructor(
    private val repository: ThemeRepository
) : UseCase<Nothing?, Flow<Theme>> {

    override fun execute(input: Nothing?): Flow<Theme> {
        return repository.observeSelectedTheme()
    }
}