package com.example.appnewnetwork.home.data

import com.example.appnewnetwork.common.base.Cache
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth

class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }

    fun createFromFeed(): HomeDataSource {
        if (feedCache.isCached()) {
            return HomeLocalDataSource(feedCache)
        } else {
            return HomeFakeRemoteDataSource()
        }
    }

}