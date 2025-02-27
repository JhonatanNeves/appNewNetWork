package com.example.appnewnetwork.profile.presenter

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
        val userUUID = DataBase.sessionAuth?.uuid ?: throw  RuntimeException("User not found")
        repository.fetchUserProfile(userUUID, object : RequestCallback<UserAuth>{
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

    override fun fetchuserPosts() {
        val userUUID = DataBase.sessionAuth?.uuid ?: throw  RuntimeException("User not found")
                repository.fetchUserPost(userUUID, object : RequestCallback<List<Post>>{
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

    override fun onDestroy() {
        view = null
    }
}