package com.dzhafarov.moneykeeper.profile.model

import java.time.LocalDate

data class UserProfile(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
)
