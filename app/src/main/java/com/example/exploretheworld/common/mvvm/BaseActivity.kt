package com.example.exploretheworld.common.mvvm

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), View {
    override fun dismissLoading() {
    }

    override fun showError(error: Throwable) {
    }

    override fun showLoading() {
    }
}