package com.example.exploretheworld.common.mvvm

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), View {
    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun showError(error: Throwable) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }
}