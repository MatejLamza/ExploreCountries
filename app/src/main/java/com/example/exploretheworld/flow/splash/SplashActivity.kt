package com.example.exploretheworld.flow.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.exploretheworld.R
import com.example.exploretheworld.common.mvvm.BaseActivity
import com.example.exploretheworld.common.state.observe
import com.example.exploretheworld.flow.host.HostActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {


    private val animationLabel: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.animate_label)
    }

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        welcomeLabel.animation = animationLabel
        bind()
    }

    private fun bind() {
        splashViewModel.state.observe(this, this) {}
    }

    override fun dismissLoading() {
        val intent = Intent(this, HostActivity::class.java)
        startActivity(intent)
    }

    override fun showError(error: Throwable) {
        error.printStackTrace()
    }
}