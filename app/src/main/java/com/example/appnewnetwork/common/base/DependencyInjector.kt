package com.example.appnewnetwork.common.base

import com.example.appnewnetwork.login.data.FakeDataSource
import com.example.appnewnetwork.login.data.LoginRepository
import com.example.appnewnetwork.register.data.FakeRegisterDataSource
import com.example.appnewnetwork.register.data.RegisterRepository

object DependencyInjector {


    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }


}