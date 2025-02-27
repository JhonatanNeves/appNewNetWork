package com.example.appnewnetwork.profile.data

import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appplantery.common.base.RequestCallback
import java.util.UUID

interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>)

    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)
}