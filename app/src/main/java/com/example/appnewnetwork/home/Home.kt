package com.example.appnewnetwork.home

import com.example.appnewnetwork.common.base.BasePresenter
import com.example.appnewnetwork.common.base.BaseView
import com.example.appnewnetwork.common.model.Post

interface Home {

    interface Presenter : BasePresenter {
        fun fetchFeed()
        fun clear()
    }

    interface  View : BaseView<Presenter>{
        fun showProgress(enabled : Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullPosts(posts: List<Post>)
    }
}