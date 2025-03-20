package com.example.appnewnetwork.common.model

import java.util.UUID

object DataBase {

    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String,MutableSet<Post>>()
    val feeds = hashMapOf<String,MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()

    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(UUID.randomUUID().toString(), "Jhonatan Neves", "userA@gmail.com", "12345678", null)
        val userB = UserAuth(UUID.randomUUID().toString(), "Criminosa", "userB@gmail.com", "87654321", null)

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

//         sessionAuth = usersAuth.first()
    }
}