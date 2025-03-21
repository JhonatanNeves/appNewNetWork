package com.example.appnewnetwork.post.data

import android.net.Uri

interface PostDataSource {
    // suspend fun usada para adiquirir dados de forma assincrona (rotina paralela/concorrente / atravez de Corontines)
    suspend fun fetchPictures() : List<Uri>
}