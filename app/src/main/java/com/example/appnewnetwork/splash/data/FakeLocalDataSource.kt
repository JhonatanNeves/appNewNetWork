package com.example.appnewnetwork.splash.data

import com.example.appnewnetwork.common.model.DataBase

class FakeLocalDataSource : SplashDataSource {
    override fun session(callback: SplashCallback) {
        if (DataBase.sessionAuth != null) {
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}