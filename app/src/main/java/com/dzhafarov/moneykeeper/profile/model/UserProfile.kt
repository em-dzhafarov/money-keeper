package com.dzhafarov.moneykeeper.profile.model

import com.dzhafarov.moneykeeper.date_time.domain.Timestamp

data class UserProfile(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Timestamp
)
