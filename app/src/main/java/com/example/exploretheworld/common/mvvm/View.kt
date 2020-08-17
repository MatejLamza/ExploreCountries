package com.example.exploretheworld.common.mvvm

interface View {
    fun showLoading()
    fun dismissLoading()
    fun showError(error: Throwable)
}