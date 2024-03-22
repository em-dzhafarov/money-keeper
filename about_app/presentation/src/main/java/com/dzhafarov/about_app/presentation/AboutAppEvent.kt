package com.dzhafarov.about_app.presentation

sealed class AboutAppEvent {
    data object Close : AboutAppEvent()
}
