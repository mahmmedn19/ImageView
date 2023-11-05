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

    protected  val _state = MutableStateFlow(initialState)
    val state: StateFlow<T> = _state

    protected val _effect = MutableSharedFlow<EventHandler<E>>()
    val effect: SharedFlow<EventHandler<E>> = _effect

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (data: T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = function()
                log("tryToExecute: $result ")
                onSuccess(result)
            } catch (exception: Throwable) {
                log("tryToExecute error: $exception")
                onError(exception)
            }
        }
    }
}
