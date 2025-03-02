package com.example.appnewnetwork.common.base

import com.example.appnewnetwork.home.data.FeedMemoryCache
import com.example.appnewnetwork.home.data.HomeDataSourceFactory
import com.example.appnewnetwork.home.data.HomeRepository
import com.example.appnewnetwork.login.data.FakeDataSource
import com.example.appnewnetwork.login.data.LoginRepository
import com.example.appnewnetwork.profile.data.PostListMemoryCache
import com.example.appnewnetwork.profile.data.ProfileDataSourceFactory
import com.example.appnewnetwork.profile.data.ProfileFakeRemoteDataSource
import com.example.appnewnetwork.profile.data.ProfileMemoryCache
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

    fun profileReposutory(): ProfileRepository {
        return ProfileRepository(
            ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache)
        )
    }

    fun homeRepository(): HomeRepository {
        return HomeRepository(
            HomeDataSourceFactory(FeedMemoryCache)
        )
    }
}