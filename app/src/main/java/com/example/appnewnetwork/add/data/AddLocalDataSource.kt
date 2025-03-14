package com.example.appnewnetwork.add.data

import com.example.appnewnetwork.common.model.DataBase
import com.example.appnewnetwork.common.model.UserAuth

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): UserAuth {
        return DataBase.sessionAuth ?: throw RuntimeException("User is not logged in!")
    }

}