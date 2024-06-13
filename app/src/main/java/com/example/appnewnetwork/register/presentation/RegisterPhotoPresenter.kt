package com.example.appnewnetwork.register.presentation

import android.net.Uri
import com.example.appnewnetwork.register.data.RegisterCallback
import com.example.appnewnetwork.register.data.RegisterRepository
import com.example.appnewnetwork.register.view.RegisterPhoto

class RegisterPhotoPresenter(
    private var view: RegisterPhoto.View?,
    private val repository: RegisterRepository
) : RegisterPhoto.Presenter {

    override fun updateUser(photoUri: Uri) {
        view?.showProgress(true)

        repository.updateUser(photoUri, object : RegisterCallback {
            override fun onSuccess() {
                view?.onUpdateSuccess()
            }

            override fun onFailure(message: String) {
                view?.onUpdateFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view = null
    }
}