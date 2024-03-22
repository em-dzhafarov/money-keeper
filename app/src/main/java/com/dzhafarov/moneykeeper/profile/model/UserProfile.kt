package com.dzhafarov.moneykeeper.profile.model

import com.dzhafarov.date_time.domain.Timestamp

data class UserProfile(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: com.dzhafarov.date_time.domain.Timestamp
)
