package com.example.appnewnetwork.post.presenter

import android.annotation.SuppressLint
import android.net.Uri
import com.example.appnewnetwork.common.model.DataBase
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appnewnetwork.post.data.PostRepository
import com.example.appnewnetwork.profile.Profile
import com.example.appnewnetwork.profile.data.ProfileRepository
import com.example.appplantery.common.base.RequestCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

// Corotine recurso acançado para executar funções em paralelo
class PostPresenter(
    private var view: com.example.appnewnetwork.post.Post.View?,
    private val repository: PostRepository
) : com.example.appnewnetwork.post.Post.Presenter, CoroutineScope {

    private var uri: Uri? = null
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
    override fun selectUri(uri: Uri) {
        this.uri = uri
    }

    override fun getSelectedUri(): Uri? {
        return uri
    }

    override fun fetchPictures() {
        // aqui acontece a chamada na thread MAIN (UI)

        launch {
            // AQUI Dentro do launch acontece a chamada paralela (corotina IO)
            view?.showProgress(true)
            val pictures = repository.fetchPictures()

            withContext(Dispatchers.Main){
                // dentro de withContext executa de volta na MainThread
                // condição para mostrar as fotos na view
                if (pictures.isEmpty()) {
                    view?.displayEmptyPictures()
                }else {
                    view?.displayFullPictures(pictures)
                }
                view?.showProgress(false)
            }

        }
    }


    override fun onDestroy() {
        job.cancel()
        view = null
    }
}