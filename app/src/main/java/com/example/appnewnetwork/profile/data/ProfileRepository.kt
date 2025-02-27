package com.example.appnewnetwork.profile.data

import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appplantery.common.base.RequestCallback

class ProfileRepository(private val dataSource: ProfileDataSource) {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>){
        dataSource.fetchUserProfile(userUUID, callback)

    }fun fetchUserPost(userUUID: String, callback: RequestCallback<List<Post>>){
        dataSource.fetchUserPosts(userUUID, callback)
    }


}