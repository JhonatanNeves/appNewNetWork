package com.example.appnewnetwork.splash

import com.example.appnewnetwork.common.base.BasePresenter
import com.example.appnewnetwork.common.base.BaseView

interface Splash {
    interface Presenter : BasePresenter {
        fun authenticated()
    }

    interface View : BaseView<Presenter> {
        fun goToHomeScreen()
        fun goToLoginScreen()
    }
}