package com.dzhafarov.settings.domain.use_case

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import com.dzhafarov.core.domain.use_case.UseCase
import javax.inject.Inject

class IsDynamicColorsSupportedUseCase @Inject constructor() : UseCase<Nothing?, Boolean> {

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    override fun execute(input: Nothing?): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }
}
