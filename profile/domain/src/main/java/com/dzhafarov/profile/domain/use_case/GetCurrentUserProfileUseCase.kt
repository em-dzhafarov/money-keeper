package com.dzhafarov.profile.domain.use_case

import com.dzhafarov.core.domain.use_case.UseCaseSuspend
import com.dzhafarov.date_time.domain.Timestamp
import com.dzhafarov.profile.domain.model.UserProfile
import javax.inject.Inject

class GetCurrentUserProfileUseCase @Inject constructor(
    // talking to repository
) : UseCaseSuspend<Nothing?, UserProfile> {

    override suspend fun execute(input: Nothing?): UserProfile {
        return UserProfile(
            firstName = "Emil",
            lastName = "Dzhafarov",
            dateOfBirth = Timestamp.of(year = 1996, month = 11, dayOfMonth = 28)
        )
    }
}
