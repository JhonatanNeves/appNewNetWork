package com.example.appnewnetwork.splash.presentation

import com.example.appnewnetwork.splash.Splash
import com.example.appnewnetwork.splash.data.SplashCallback
import com.example.appnewnetwork.splash.data.SplashRepository


class SplashPresenter (
    private var view: Splash.View?,
    private var repository: SplashRepository
    ): Splash.Presenter {

    override fun onDestroy() {
        view = null
    }

    override fun authenticated() {
        repository.session(object : SplashCallback {
            override fun onSuccess() {
                view?.goToHomeScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }
        })

    }
}