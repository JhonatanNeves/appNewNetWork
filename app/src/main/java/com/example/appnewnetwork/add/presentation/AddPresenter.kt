package com.example.appnewnetwork.add.presentation

import android.net.Uri
import com.example.appnewnetwork.add.Add
import com.example.appnewnetwork.add.data.AddRepository
import com.example.appplantery.common.base.RequestCallback

class AddPresenter(
    private var view : Add.View? = null,
    private val repository: AddRepository
) : Add.Presenter {
    override fun createPost(uri: Uri, caption: String) {
        view?.showProgress(true)
        repository.createPost(uri, caption, object : RequestCallback<Boolean>{
            override fun onSuccess(data: Boolean) {
                if (data){
                    view?.displayRequestSuccess()
                } else {
                    view?.displayRequestFailure("Internal Error!")
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