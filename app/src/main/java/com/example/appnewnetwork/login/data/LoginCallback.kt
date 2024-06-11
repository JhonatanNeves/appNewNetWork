package com.example.appnewnetwork.login.data

import com.example.appnewnetwork.common.model.UserAuth

interface LoginCallback {
    fun onSuccess(userAuth: UserAuth)
    fun onFailure(message: String)
    fun onComplete()
}