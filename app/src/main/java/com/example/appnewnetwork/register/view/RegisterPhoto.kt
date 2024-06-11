package com.example.appnewnetwork.register.view

import android.net.Uri
import androidx.annotation.StringRes
import com.example.appnewnetwork.common.base.BasePresenter
import com.example.appnewnetwork.common.base.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter {
        fun updateUser(photoUri: Uri)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)

        fun onUpdateFailure(message: String)

        fun onUpdateSuccess()
    }

}