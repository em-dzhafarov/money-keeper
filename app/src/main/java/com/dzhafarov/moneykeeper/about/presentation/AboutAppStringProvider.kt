package com.dzhafarov.moneykeeper.about.presentation

interface AboutAppStringProvider {
    suspend fun title(): String
    suspend fun text(): String
    suspend fun confirm(): String
}