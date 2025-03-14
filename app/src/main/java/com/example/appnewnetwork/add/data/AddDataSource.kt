package com.example.appnewnetwork.add.data

import android.net.Uri
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appplantery.common.base.RequestCallback
import java.lang.UnsupportedOperationException
import java.util.UUID

interface AddDataSource {

    fun createPost(userUUID: String, uri: Uri, caption:String, callback: RequestCallback<Boolean>) { throw UnsupportedOperationException() }

    fun fetchSession() : UserAuth { throw  UnsupportedOperationException()}

}