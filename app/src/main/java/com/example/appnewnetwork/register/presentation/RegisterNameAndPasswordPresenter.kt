package com.example.appnewnetwork.register.presentation

import com.example.appnewnetwork.R
import com.example.appnewnetwork.register.RegisterNameAndPassword
import com.example.appnewnetwork.register.data.RegisterCallback
import com.example.appnewnetwork.register.data.RegisterRepository

class RegisterNameAndPasswordPresenter(
    private var view: RegisterNameAndPassword.View?,
    private val repository: RegisterRepository
) : RegisterNameAndPassword.Presenter {

    override fun create(email: String, name: String, password: String, confirm: String) {

        val isNameValid = name.length > 3
        val isPasswordValid = password.length >= 8
        val isConfirmValid = password == confirm

        if (!isNameValid) {
            view?.displayNameFailure(R.string.invalid_name)
        } else {
            view?.displayNameFailure(null)
        }

        if (!isConfirmValid) {
            view?.displayPasswordFailure(R.string.password_not_equal)
        } else {
            if (!isPasswordValid) {
                view?.displayPasswordFailure(R.string.invalid_password)
            } else {
                view?.displayPasswordFailure(null)
            }
        }

        if (isNameValid && isPasswordValid && isConfirmValid) {
            view?.showProgress(true)

            repository.create(email, name, password, object : RegisterCallback {
                override fun onSuccess() {
                    view?.onCreateSuccess(name)
                }

                override fun onFailure(message: String) {
                    view?.onCreateFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }

    }

    override fun onDestroy() {
        view = null
    }
}