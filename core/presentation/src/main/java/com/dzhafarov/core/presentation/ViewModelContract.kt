package com.dzhafarov.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

abstract class ViewModelContract<out S, out E, in A> : ViewModel(), CoroutineScope {
    abstract val state: StateFlow<S>
    abstract val events: Flow<E>
    abstract fun reduce(action: A)

    override val coroutineContext: CoroutineContext = SupervisorJob() +
            Dispatchers.Main.immediate +
            CoroutineExceptionHandler { _, error -> error.printStackTrace() }
}
