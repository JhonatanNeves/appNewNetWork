package com.example.appnewnetwork.home.data

import com.example.appnewnetwork.common.base.Cache
import com.example.appnewnetwork.common.model.DataBase
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appplantery.common.base.RequestCallback

class HomeLocalDataSource(
    private val feedcache: Cache<List<Post>>
) : HomeDataSource {

    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = feedcache.get(userUUID)
        if (posts != null) {
            callback.onSuccess(posts)
        } else {
            callback.onFailure("Posts not found")
        }
        callback.onComplete()
    }



    override fun fetchSession(): UserAuth {
        return DataBase.sessionAuth ?: throw RuntimeException("User not logged in.")
    }

    override fun putFeed(response: List<Post>) {
        feedcache.put(response)
    }

}