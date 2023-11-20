package com.dzhafarov.moneykeeper.settings.domain.use_case

import android.os.Build
import com.dzhafarov.moneykeeper.core.domain.use_case.UseCase
import javax.inject.Inject

class IsDynamicColorsSupportedUseCase @Inject constructor() : UseCase<Nothing?, Boolean> {

    override fun execute(input: Nothing?): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }
}
