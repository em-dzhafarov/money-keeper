package com.dzhafarov.about_app.presentation

interface AboutAppStringProvider {
    suspend fun title(): String
    suspend fun text(): String
    suspend fun confirm(): String
}