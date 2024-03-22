package com.dzhafarov.core.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ViewModelContract<out S, out E, in A> {
    val state: StateFlow<S>
    val events: Flow<E>
    fun reduce(action: A)
}
