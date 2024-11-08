package com.dzhafarov.home.presentation

sealed class HomeUiAction {
    data object OnAddExpenseClick : HomeUiAction()
    data class OnExpenseEditClick(val id: Long) : HomeUiAction()
    data class OnExpenseDeleteSwipe(val id: Long) : HomeUiAction()
    data object OnViewLookingClick : HomeUiAction()
    data object OnSettingsClick : HomeUiAction()
    data object OnFilterClick : HomeUiAction()
    data object OnSearchClick : HomeUiAction()
    data object OnDashboardClick : HomeUiAction()
}
