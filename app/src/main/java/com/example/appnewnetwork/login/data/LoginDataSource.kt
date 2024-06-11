package com.example.appnewnetwork.login.data

interface LoginDataSource {

    fun login(email: String, password: String, callback: LoginCallback)

}