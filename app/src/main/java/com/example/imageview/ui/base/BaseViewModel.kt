package com.example.imageview.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageview.ui.utils.EventHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T, E>(initialState: T) : ViewModel() {

    abstract val TAG: String

    protected open fun log(message: String) {
        Log.v(TAG, message)
    }

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<T> = _state

    private val _effect = MutableSharedFlow<EventHandler<E>>()
    val effect: SharedFlow<EventHandler<E>> = _effect

    protected fun tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = function()
                Log.d(TAG, "tryToExecute: $result")
                onSuccess(result)
            } catch (e: Exception) {
                Log.e(TAG, "tryToExecute: ${e.message}")
                onError(e)
            }
        }
    }
}
