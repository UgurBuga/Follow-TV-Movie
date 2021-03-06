package com.ugurbuga.followtvmovie.watch.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect


fun <T> LifecycleOwner.collect(stateFlow: StateFlow<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        stateFlow.collect { t -> observer(t) }
    }
}