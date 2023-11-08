package com.dzhafarov.moneykeeper.profile.use_case

import com.dzhafarov.moneykeeper.core.domain.use_case.UseCase
import com.dzhafarov.moneykeeper.date_time.domain.Timestamp
import com.dzhafarov.moneykeeper.profile.model.UserProfile
import javax.inject.Inject

class GetCurrentUserProfileUseCase @Inject constructor(
    // talking to repository
) : UseCase<Nothing?, UserProfile> {

    override suspend fun execute(input: Nothing?): UserProfile {
        return UserProfile(
            firstName = "Emil",
            lastName = "Dzhafarov",
            dateOfBirth = Timestamp.of(year = 1996, month = 11, dayOfMonth = 28)
        )
    }
}
