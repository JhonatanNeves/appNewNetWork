package com.example.appnewnetwork.home.data

import com.example.appnewnetwork.common.base.Cache
import com.example.appnewnetwork.common.model.Post

object FeedMemoryCache : Cache<List<Post>> {

    private var posts: List<Post>? = null
    override fun isCached(): Boolean {
        return posts != null
    }

    override fun get(key: String): List<Post>? {
        return posts
    }

    override fun put(data: List<Post>?) {
        posts = data
    }

}