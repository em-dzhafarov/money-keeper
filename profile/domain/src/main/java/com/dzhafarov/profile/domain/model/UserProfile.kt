package com.dzhafarov.profile.domain.model

import com.dzhafarov.date_time.domain.Timestamp

data class UserProfile(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Timestamp
)
