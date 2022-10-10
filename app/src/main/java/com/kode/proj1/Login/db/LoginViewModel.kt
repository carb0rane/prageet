package com.kode.proj1.Login.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private const val TAG = "LoginViewModel"
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var loginflag: String = "normal"
     lateinit var loginrepo: LoginRepo
    private var usee: UserProfile? = null

    init {
        val loginDao = LoginDatabase.getLoginDatabaseInstance(application).getLoginDao()
        loginrepo = LoginRepo(loginDao)
    }


   suspend fun logincheck(userinfo: UserProfile) =viewModelScope.async{
       loginrepo.check(userinfo)
        }.await()

     //   return loginflag
   // }

    fun registerUser(userinfo: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            val ppoid=loginrepo.registerUser(userinfo)
            Log.d(TAG, "registerUser: The ID is : $ppoid")
        }

    }

    fun getAllUsers(): UserProfile? {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Inside LoginViewModel ", "getAllUsers: This is just here")
            usee = loginrepo.getAllUsers()
        }
        return usee
    }

}