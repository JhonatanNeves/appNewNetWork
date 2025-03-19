package com.example.appnewnetwork.profile.presenter

import android.annotation.SuppressLint
import com.example.appnewnetwork.common.model.DataBase
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appnewnetwork.profile.Profile
import com.example.appnewnetwork.profile.data.ProfileRepository
import com.example.appplantery.common.base.RequestCallback
import java.lang.RuntimeException

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override fun fetchuserProfile() {
        view?.showProgress(true)
        repository.fetchUserProfile(object : RequestCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
            }

        })
    }

    @SuppressLint("SuspiciousIndentation")
    override fun fetchuserPosts() {
                repository.fetchUserPost(object : RequestCallback<List<Post>>{
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