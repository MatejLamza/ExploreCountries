package com.example.exploretheworld.common.mvvm

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), View {
    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun showError(error: Throwable) {
    }

}