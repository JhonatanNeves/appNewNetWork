package com.example.appnewnetwork.register.data

import android.net.Uri
import com.example.appnewnetwork.register.data.RegisterCallback

interface RegisterDataSource {
    fun create(email: String, callback: RegisterCallback)

    fun create(email: String, name: String, password: String, callback: RegisterCallback)

    fun updateUser(photoUri: Uri, callback: RegisterCallback)
}