package com.example.appnewnetwork.common.model

import android.net.Uri

data class Post(
    val uuid: String,
    val uri: Uri,
    val captinon: String,
    val timestamp: Long,
    val publisher: UserAuth
)
