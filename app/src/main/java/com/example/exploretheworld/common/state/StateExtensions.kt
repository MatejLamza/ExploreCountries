package com.example.exploretheworld.common.state

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.observe
import com.example.exploretheworld.common.mvvm.View
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun LiveData<State>.observe(
    owner: LifecycleOwner,
    view: View? = null,
    onError: ((error: Throwable) -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
    onDone: ((hasData: Boolean?) -> Unit)? = null
) {
    observe(owner) { state ->
        when (state) {
            is Loading -> onLoading?.invoke() ?: view?.showLoading()
            is Done -> {
                view?.dismissLoading()
                onDone?.invoke(state.hasData)
            }
            is Error -> {
                view?.dismissLoading()
                state.throwable?.let { onError?.invoke(it) } ?: state.throwable?.let { error ->
                    view?.showError(
                        error
                    )
                }
            }
        }
    }
}

fun exceptionHandler(onError: ((Throwable) -> Unit)) =
    CoroutineExceptionHandler { _, exception ->
        Log.e("ViewModel", "Error in ViewModel", exception)
        onError(exception)
    }

fun exceptionHandler(data: MutableLiveData<State>? = null) =
    exceptionHandler { data?.postValue(Error(it)) }

fun ViewModel.launch(
    data: MutableLiveData<State>,
    context: CoroutineContext = exceptionHandler(data),
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context, start) {
    data.value = Loading
    block(this)
}

fun ViewModel.launch(
    context: CoroutineContext = exceptionHandler(null),
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context, start) {
    block(this)
}

fun ViewModel.launchWithState(
    data: MutableLiveData<State>,
    onLoading: (() -> Unit)? = { data.value = Loading },
    onError: ((Throwable) -> Unit)? = { data.value = Error(it) },
    onDone: (() -> Unit)? = { data.value = Done() },
    context: CoroutineContext =
        if (onError != null) exceptionHandler(onError)
        else exceptionHandler(data),
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context, start) {
    onLoading?.invoke()
    block(this)
    onDone?.invoke()
}

