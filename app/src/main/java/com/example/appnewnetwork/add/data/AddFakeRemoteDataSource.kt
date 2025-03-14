package com.example.appnewnetwork.add.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.example.appnewnetwork.common.model.DataBase
import com.example.appnewnetwork.common.model.Post
import com.example.appplantery.common.base.RequestCallback
import java.util.UUID

class AddFakeRemoteDataSource : AddDataSource{
    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            var posts = DataBase.posts[userUUID]

            if (posts == null) {
                posts = mutableSetOf()
                DataBase.posts[userUUID] = posts
            }

            val post = Post(UUID.randomUUID().toString(), uri, caption, System.currentTimeMillis(), DataBase.sessionAuth!!)

            posts.add(post)

            var followers = DataBase.followers[userUUID]

            if (followers == null) {
                followers = mutableSetOf()
                DataBase.followers[userUUID] = followers
            }else {
                for (follower in followers) {
                    DataBase.feeds[follower]?.add(post)
                }
                DataBase.feeds[userUUID]?.add(post)
            }

            callback.onSuccess(true)

        }, 1000)
    }
}