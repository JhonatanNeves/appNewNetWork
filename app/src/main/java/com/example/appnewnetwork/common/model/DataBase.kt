package com.example.appnewnetwork.common.model

import java.util.UUID

object DataBase {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String,Set<Post>>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Jhonatan Neves", "userA@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "Criminosa", "userB@gmail.com", "87654321"))

//        sessionAuth = usersAuth.first()
    }
}