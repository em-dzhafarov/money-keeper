package com.dzhafarov.moneykeeper.home.presentation

sealed class HomeAction {
    object AddExpense : HomeAction()
    object OpenNotifications : HomeAction()
    object OpenAboutAppInfo : HomeAction()
}
