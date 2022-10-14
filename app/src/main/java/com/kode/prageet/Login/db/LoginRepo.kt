package com.kode.prageet.Login.db

import android.util.Log

private const val TAG = "LoginRepo"

class LoginRepo(private val loginDAO: LoginDAO) {
    private var passis: String = "d"

    suspend fun check(userinfo: UserProfile): String {
        Log.d(TAG, "check: ${userinfo.userEmail}")
        val piss= loginDAO.checkUserEmail(userinfo.userEmail)
        Log.d(TAG, "check: the val is : $piss")
    // return passis
        return piss

    }

    suspend fun registerUser(userinfo: UserProfile): Long {

        return loginDAO.registerUser(userinfo)
    }

    suspend fun getAllUsers(): UserProfile {
        Log.d("Inside LoginRepo ", "getAllUsers: This is just here")

        return loginDAO.getAllUsers()
    }
}