package com.example.appnewnetwork.profile.data

import com.example.appnewnetwork.common.base.Cache
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: Cache<UserAuth>,
    private val postsCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(): ProfileDataSource {
        if (profileCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        } else {
            return ProfileFakeRemoteDataSource()
        }
    }

    fun createFromPosts(): ProfileDataSource {
        if (postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        } else {
            return ProfileFakeRemoteDataSource()
        }
    }

}