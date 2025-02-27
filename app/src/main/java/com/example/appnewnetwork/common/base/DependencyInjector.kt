package com.example.appnewnetwork.common.base

import com.example.appnewnetwork.login.data.FakeDataSource
import com.example.appnewnetwork.login.data.LoginRepository
import com.example.appnewnetwork.profile.data.ProfileFakeRemoteDataSource
import com.example.appnewnetwork.profile.data.ProfileRepository
import com.example.appnewnetwork.register.data.FakeRegisterDataSource
import com.example.appnewnetwork.register.data.RegisterRepository
import com.example.appnewnetwork.splash.data.FakeLocalDataSource
import com.example.appnewnetwork.splash.data.SplashRepository

object DependencyInjector {


    fun splashRepository(): SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }

    fun profileReposutory() : ProfileRepository {
        return ProfileRepository(ProfileFakeRemoteDataSource())
    }

}