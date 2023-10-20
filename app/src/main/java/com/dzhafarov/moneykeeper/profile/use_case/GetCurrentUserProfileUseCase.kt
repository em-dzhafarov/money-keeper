package com.dzhafarov.moneykeeper.profile.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCase
import com.dzhafarov.moneykeeper.profile.model.UserProfile
import java.time.LocalDate
import javax.inject.Inject

class GetCurrentUserProfileUseCase @Inject constructor(
    // talking to repository
) : UseCase<Nothing?, UserProfile> {

    override suspend fun execute(input: Nothing?): UserProfile {
        return UserProfile(
            firstName = "Emil",
            lastName = "Dzhafarov",
            dateOfBirth = LocalDate.of(1996, 11, 28)
        )
    }
}
