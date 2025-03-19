package com.example.appnewnetwork.home.data

import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appplantery.common.base.RequestCallback
import java.lang.UnsupportedOperationException
import java.util.UUID

interface HomeDataSource {

    fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)

    fun fetchSession() : UserAuth { throw UnsupportedOperationException() }

    fun putFeed(response: List<Post>?) { throw UnsupportedOperationException() }
}