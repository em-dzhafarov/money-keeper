package com.dzhafarov.home.presentation

enum class ExpensesDisplayMode {
    LIST, GRID;

    fun isList() = (this === LIST)

    fun isGrid() = (this === GRID)
}