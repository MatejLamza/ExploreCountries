package com.example.exploretheworld.common.state

sealed class State

object Loading : State()
data class Done(val hasData: Boolean? = null) : State()
data class Error(val throwable: Throwable? = null) : State()