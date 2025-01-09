package com.example.appnewnetwork.splash.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.appnewnetwork.R
import com.example.appnewnetwork.common.base.DependencyInjector
import com.example.appnewnetwork.common.extension.animationEnd
import com.example.appnewnetwork.databinding.ActivitySplashBinding
import com.example.appnewnetwork.login.view.LoginActivity
import com.example.appnewnetwork.main.view.MainActivity
import com.example.appnewnetwork.splash.Splash
import com.example.appnewnetwork.splash.presentation.SplashPresenter

class SplashActivity : AppCompatActivity(), Splash.View {

    private lateinit var binding: ActivitySplashBinding
    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_primary)

        val repository = DependencyInjector.splashRepository()
        presenter = SplashPresenter(this, repository)
        binding.splashProgressBar.animate().apply {
            setListener(animationEnd {
                presenter.authenticated()
            })
            duration = 2000
            alpha(1.0f)
            start()
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

     override fun goToHomeScreen() {
        binding.splashProgressBar.animate().apply {
            setListener(animationEnd {
                val intent = Intent(baseContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }
    }

    override fun goToLoginScreen() {
        binding.splashProgressBar.animate().apply {
            setListener(animationEnd {
                val intent = Intent(baseContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }
    }

}