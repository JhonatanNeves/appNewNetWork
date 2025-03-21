package com.example.appnewnetwork.post.data

import android.net.Uri

class PostRepository(private val dataSource: PostDataSource) {

    suspend fun fetchPictures() = dataSource.fetchPictures()

}