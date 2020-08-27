package com.example.exploretheworld.flow.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.exploretheworld.R
import com.example.exploretheworld.common.mvvm.BaseActivity
import com.example.exploretheworld.flow.host.HostActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashActivity : BaseActivity() {

    private val animationButton: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.animate_button)
    }
    private val animationLabel: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.animate_label)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getStarted.animation = animationButton
        welcomeLabel.animation = animationLabel
        setupUI()
    }

    private fun setupUI() {
        getStarted.setOnClickListener {
            val intent = Intent(this, HostActivity::class.java)
            startActivity(intent)
        }
    }
}