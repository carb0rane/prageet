package com.kode.prageet.AppStatus

class AppStatusRepo(private val appStatusDAO: AppStatusDAO) {

  suspend fun isUserLoggedIn():Boolean {

      return appStatusDAO.isUserLoggedIn()
  }


    suspend fun addAppStatus(appStatus: AppStatus){
        appStatusDAO.addAppStatus(appStatus)
    }

}