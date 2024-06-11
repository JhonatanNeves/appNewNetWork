package com.example.appnewnetwork.register

import androidx.annotation.StringRes
import com.example.appnewnetwork.common.base.BasePresenter
import com.example.appnewnetwork.common.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter {
        fun create(email: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)

        fun displayEmailFailure(@StringRes emailError: Int?)

        fun onEmailFailure(message: String)

        fun goToNameAndPassWordScreen(email: String)
    }

}