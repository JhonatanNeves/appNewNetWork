package com.example.appnewnetwork.home.presenter

import android.annotation.SuppressLint
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appnewnetwork.home.Home
import com.example.appnewnetwork.home.data.HomeRepository
import com.example.appnewnetwork.profile.Profile
import com.example.appnewnetwork.profile.data.ProfileRepository
import com.example.appplantery.common.base.RequestCallback

class HomePresenter(
    private var view: Home.View?,
    private val repository: HomeRepository
) : Home.Presenter {

    override fun fetchFeed() {
        view?.showProgress(true)
        repository.fetchFeed(object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPost()
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }

    override fun clear() {
        repository.clearCache()
    }

    override fun onDestroy() {
        view = null
    }
}
