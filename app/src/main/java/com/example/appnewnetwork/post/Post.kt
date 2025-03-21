package com.example.appnewnetwork.post

import android.net.Uri
import com.example.appnewnetwork.common.base.BasePresenter
import com.example.appnewnetwork.common.base.BaseView
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth

interface Post {
    interface Presenter: BasePresenter {
        fun fetchPictures()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled : Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPictures()
        fun displayFullPictures(posts: List<Uri>)
    }
}