package com.example.appnewnetwork.profile

import com.example.appnewnetwork.common.base.BasePresenter
import com.example.appnewnetwork.common.base.BaseView
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth

interface Profile {

    interface Presenter : BasePresenter{
        fun fetchuserProfile()
        fun fetchuserPosts()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled : Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)

        fun displayEmptyPost()

        fun displayFullPosts(posts: List<Post>)

    }

}