package com.example.appnewnetwork.profile.data

import com.example.appnewnetwork.common.base.Cache
import com.example.appnewnetwork.common.model.DataBase
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appplantery.common.base.RequestCallback

class ProfileLocalDataSource(
    private val profileCache: Cache<UserAuth>,
    private val postsCache: Cache<List<Post>>
) : ProfileDataSource {
    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>) {
        val userAuth = profileCache.get(userUUID)
        if (userAuth != null){
            callback.onSuccess(userAuth)
        } else {
            callback.onFailure("User not found")
        }
        callback.onComplete()
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = postsCache.get(userUUID)
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

    override fun putUser(response: UserAuth) {
        profileCache.put(response)
    }

    override fun putPosts(response: List<Post>?) {
        postsCache.put(response)
    }

}