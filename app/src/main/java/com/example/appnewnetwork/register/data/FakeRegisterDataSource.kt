package com.example.appnewnetwork.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.example.appnewnetwork.common.model.DataBase
import com.example.appnewnetwork.common.model.UserAuth
import java.util.UUID

class FakeRegisterDataSource : RegisterDataSource {
    override fun create(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("User already registered")
            }

            callback.onComplete()
        }, 2000)
    }

    override fun create( email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.usersAuth.firstOrNull { email == it.email }

            if (userAuth != null) {
                callback.onFailure("User already registered")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password, null)
                val created = DataBase.usersAuth.add(newUser)

                if (created) {
                    DataBase.sessionAuth = newUser

                    DataBase.followers[newUser.uuid] = hashSetOf()
                    DataBase.posts[newUser.uuid] = hashSetOf()
                    DataBase.feeds[newUser.uuid] = hashSetOf()

                    callback.onSuccess()
                } else {
                    callback.onFailure("Internal server error")
                }

            }

            callback.onComplete()
        }, 2000)
    }


    override fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = DataBase.sessionAuth

            if (userAuth == null) {
                callback.onFailure("User not found")
            } else {
                val index = DataBase.usersAuth.indexOf(DataBase.sessionAuth)
                DataBase.usersAuth[index] = DataBase.sessionAuth!!.copy(photoUri = photoUri)
                DataBase.sessionAuth = DataBase.usersAuth[index]

                    callback.onSuccess()
            }
            callback.onComplete()
        }, 2000)
    }
}